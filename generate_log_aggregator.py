import random

filename = "log_aggregator_input.txt"
target_size_mb = 10
target_size_bytes = target_size_mb * 1024 * 1024

statuses = ["ERROR", "INFO", "WARN", "DEBUG"]

error_messages = [
    "Connection reset",
    "Timeout",
    "Database unavailable",
    "Authentication failed",
    "Out of memory",
    "Disk full",
    "Service unavailable",
    "Invalid request",
]

info_messages = [
    "Started job",
    "Completed",
    "Processing request",
    "User logged in",
    "Cache refreshed",
    "Health check passed",
]

garbage_lines = [
    "junk data",
    "random garbage here",
    "",
    "   ",
    "malformed [line",
    "no brackets at all",
    "[only one bracket",
    "missing status] message",
    "12:00:00 ERROR no brackets",
]

# Make "Connection reset" and "Timeout" the top 2 errors
winner_errors = ["Connection reset", "Timeout"]

print(f"Generating {filename} targeting {target_size_mb}MB...")

current_size = 0
hour = 12
minute = 0
second = 0

with open(filename, "w") as f:
    while current_size < target_size_bytes:
        # 5% chance of garbage line
        if random.random() < 0.05:
            line = random.choice(garbage_lines) + "\n"
        else:
            # Generate timestamp
            timestamp = f"{hour:02d}:{minute:02d}:{second:02d}"

            # Pick status (40% ERROR, 40% INFO, 20% other)
            r = random.random()
            if r < 0.4:
                status = "ERROR"
                # Bias toward winner errors
                if random.random() < 0.6:
                    message = random.choice(winner_errors)
                else:
                    message = random.choice(error_messages)
            elif r < 0.8:
                status = "INFO"
                message = random.choice(info_messages)
            else:
                status = random.choice(["WARN", "DEBUG"])
                message = random.choice(info_messages + error_messages)

            line = f"[{timestamp}] {status} {message}\n"

        f.write(line)
        current_size += len(line)

        # Advance time
        second += random.randint(0, 2)
        if second >= 60:
            second = 0
            minute += 1
        if minute >= 60:
            minute = 0
            hour += 1
        if hour >= 24:
            hour = 0

actual_size_mb = current_size / (1024 * 1024)
print(f"Done. Generated {actual_size_mb:.2f}MB")
print(f"Expected top errors: {winner_errors}")
