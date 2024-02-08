import itertools
import hashlib
import math
import sys
from threading import Thread
import time

pw_hash = "755afdd46a18a25bd85ddd4004d5cfea"
time_taken = 0
length = 5
threads = 8
start = 0

# compare guess_hash to pw_hash
def compare(word):

    # convert the word to a hash
    guess = word.encode()
    guess_hash = hashlib.md5(guess).hexdigest()
    
    if guess_hash == pw_hash:
                
        # calculate time taken
        global time_taken
        time_taken = time.time() - start
        
        # printout
        print(f'Password found: {word}')
        print(f'Time taken: {time_taken} seconds')
        print()
        sys.exit(0)
    

def crack(start, end, length):
    full_range = "abcdefghijklmnopqrstuvwxyz0123456789"
    section = full_range[start:end]
    for combo in itertools.product(section, repeat=1):
        for i in itertools.product(full_range, repeat=length-1):
            word = combo[0] + ''.join(i)
            compare(word)
        
            
               
if __name__ == "__main__":
    
    print()
    print("Cracking process initialized ...")
    
    start = time.time()
    
    range_size = math.ceil(length / threads)
    # array of all combinations
    t1 = Thread(target=compare, args=(crack(0, range_size, length)))
    t2 = Thread(target=compare, args=(crack(range_size, 2 * range_size, length)))
    t3 = Thread(target=compare, args=(crack(2 * range_size, 3 * range_size, length)))
    t4 = Thread(target=compare, args=(crack(3 * range_size, 4 * range_size, length)))
    t5 = Thread(target=compare, args=(crack(4 * range_size, 5 * range_size, length)))
    t6 = Thread(target=compare, args=(crack(5 * range_size, 6 * range_size, length)))
    t7 = Thread(target=compare, args=(crack(6 * range_size, 7 * range_size, length)))
    t8 = Thread(target=compare, args=(crack(7 * range_size, 36, length)))
    
    # start all threads
    t1.start()
    t2.start()
    t3.start()
    t4.start()
    t5.start()
    t6.start()
    t7.start()
    t8.start()
    
    

    
