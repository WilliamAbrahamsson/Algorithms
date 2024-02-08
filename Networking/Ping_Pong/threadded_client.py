import json
import requests
import time
import threading

endpoint = 'http://localhost:8000/ping-endpoint'

# time between pings.
ping_delay = 60

# time until termination.
timeout_delay = 120

def ping_server():
    last_res_time = time.time()

    while True:
        try:
            res = requests.get(endpoint, timeout=5)
            res.raise_for_status()

            # get and print res.
            data = res.text
            pong = json.loads(data)
            print(f'pong_res: {pong}')

            # Update last res time.
            last_res_time = time.time()

        except requests.RequestException as e:
            elapsed_time = time.time() - last_res_time

            # print time since last res.
            print("no res in " + str(elapsed_time)[0:4] + " seconds")

            # print error.
            print(e)

        time.sleep(ping_delay)
        elapsed_time = time.time() - last_res_time
        if elapsed_time >= timeout_delay:
            print("terminating... (no res in: " +
                  str(elapsed_time)[0:4] + " seconds)")
            break

# setup 1 thd.
thread = threading.Thread(target=ping_server)
thread.start()
thread.join()
