import itertools
import hashlib
import math
import sys
from multiprocessing import Process
import time

pw_hash = "7fb3cea8cf75cf4959a1ab8b222ed28c"
length = 6
threads = 8
pw_found = False

def compare(word, start_time):
    guess = word.encode()
    guess_hash = hashlib.md5(guess).hexdigest()
    
    if guess_hash == pw_hash:
        
        global time_taken
        time_taken = time.time() - start_time
        
        print(f'Password found: {word}')
        print(f'Time taken: {time_taken} seconds')

        print()
        sys.exit(0)
    

def crack(start, end, length, start_time):
    full_range = "abcdefghijklmnopqrstuvwxyz0123456789"
    section = full_range[start:end]
    for combo in itertools.product(section, repeat=1):
        for i in itertools.product(full_range, repeat=length-1):
            word = combo[0] + ''.join(i)
            compare(word, start_time)

if __name__ == "__main__":
    print()
    print("Cracking process initialized ...")
    start_time = time.time()
        
    range_size = math.ceil(36 / threads)
    p1 = Process(target=crack, args=(0, range_size, length, start_time))
    p2 = Process(target=crack, args=(range_size, 2 * range_size, length, start_time))
    p3 = Process(target=crack, args=(2 * range_size, 3 * range_size, length, start_time))
    p4 = Process(target=crack, args=(3 * range_size, 4 * range_size, length, start_time))
    p5 = Process(target=crack, args=(4 * range_size, 5 * range_size, length, start_time))
    p6 = Process(target=crack, args=(5 * range_size, 6 * range_size, length, start_time))
    p7 = Process(target=crack, args=(6 * range_size, 7 * range_size, length, start_time))
    p8 = Process(target=crack, args=(7 * range_size, 36, length, start_time))
    
    p1.start()
    p2.start()
    p3.start()
    p4.start()
    p5.start()
    p6.start()
    p7.start()
    p8.start()


# Cracking process initialized ...
# Password found: 1dt909
# Time taken: 211.6399848461151 seconds

# Cracking process initialized ...
# Password found: 1dt909
# Time taken: 206.73327493667603 seconds

# Cracking process initialized ...
# Password found: 1dt901
# Time taken: 205.67953610420227 seconds