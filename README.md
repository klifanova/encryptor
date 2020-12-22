# Encryptor
## Data Encoding / Decoding System
Console application that allows encryption and decryption of data by shifting characters.
The program must support 2 types of algorithm:
1) Shift according to ASCII table (UNICODE);
2) Shift in the English alphabet (SHIFT) in case of going out of bounds, go to the opposite end of the alphabet (z-> a), do not encrypt characters other than letters;

Required command line arguments (The order of parameters can be derived):

* `-mode` control command, takes two values ​​`enc / dec`, where enc = encryption, dec = decryption
* `-in` path to the file with data for processing
* `-out` path to the file where you want to save the result (create if missing)
* `-key` key to shift (the number of positions to shift the character)
* `-alg` type of algorithm, accepts one of two `unicode / shift` values
* `-data` data to encrypt

If the `-mode` parameter is not passed, the program should run in encryption mode;\
If the `-key` parameter is not passed, treat it as zero;\
If the `-data` parameter is not specified, as well as `-in` is absent, then take an empty string for data;\
If the `-out` parameter is not specified, output the data to the standard output stream;\
If both `-data` and `-in` are supplied, give preference to data from the `-data` parameter;\
If the `-alg` parameter is not specified, we work with the shift algorithm.

An example of how the algorithms work:
```
UNICODE (shift by 5): Lets Encrypt -> Qjyx% Jshw ~ uy
SHIFT (shift 7): Lets Encrypt -> Slaz Lujyfwa
```
An example of passed parameters:
```
-mode enc -key 5 -data "Lets Encrypt" -alg unicode -in input.txt -out output.txt
```
##Technical Requirements

* Java 11
* Maven 3.3+
* Git

##How to build

`mvn clean install`

##How to run

Command to run application:

`java -jar target/encryptor-XXX.jar`