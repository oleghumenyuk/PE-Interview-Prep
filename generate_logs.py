import random

filename = "app_logs.txt"
total_lines = 50000
window_size = 600  # 10 minutes

# Error codes to simulate
errors = ["DB_CONNECTION_FAIL", "TIMEOUT", "500_INTERNAL_ERROR",
          "403_FORBIDDEN", "CACHE_MISS", "SERVICE_UNAVAILABLE"]

# The error we want to "win" in the last 10 minutes
winner_error = "TIMEOUT"

print(f"Generating {filename} with {total_lines} lines...")

with open(filename, "w") as f:
    # Start at an arbitrary timestamp (e.g., yesterday)
    current_time = 1625000000

    # Track counts for the final validation
    window_counts = {}

    for i in range(total_lines):
        # 1. Monotonically increase time (Sorted Order)
        # Advance time by 0-3 seconds randomly
        current_time += random.randint(0, 3)

        # 2. Determine if we are in the "Target Window"
        # We don't know the exact final time yet, but we can force bias
        # near the end of the loop.
        is_near_end = i > (total_lines - 1000)

        if is_near_end:
            # BIAS: Make the 'winner_error' appear 50% of the time at the end
            if random.random() < 0.5:
                chosen_error = winner_error
            else:
                chosen_error = random.choice(errors)
        else:
            # NOISE: Random distribution for the rest of the file
            chosen_error = random.choice(errors)

        # 3. Handle Edge Case: Colon in error name
        # Occasionally change "TIMEOUT" to "TIMEOUT:CRITICAL" to test your parsing
        if random.random() < 0.05:
            chosen_error += ":CRITICAL"

        f.write(f"{chosen_error}:{current_time}\n")

print(f"Done.")
print(f"The file '{filename}' is ready.")
print(f"Expected Winner (High Probability): {winner_error}")
