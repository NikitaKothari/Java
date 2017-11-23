G32 - Vinaya Ganiga, Pradeep Nair, Mohanakrishna Vanamala Hariprasad, Nikita Kothari

Level 1:

All required methods are implemented in Num.java

Default base is 2^16

There are two constructors one accepts a string and the other a long. The string has to be a valid sequence of digits and can optionally start with + or -. In case the string input is bad then a RuntimeException is thrown

All methods viz. add(Num a, Num b), subtract(Num a, Num b), product(Num a, Num b), power(Num x, long n) are static and return a Num value so you can use them as 
Num x = Num.add(Num a, Num b);

product uses Karatsuba multiplication and if the second operand list size is 1 then just falls back to normal multiplication which is faster in such a case

to use printList() call the method using a object of the class, it will give the output as desiered

to use toString() just use the Object of the class in the System.out.println().

A simple example of add, printList and toString are provided in LP1L1.java

Level 2:

Implemented methods power(Num x, Num n), divide(Num a, Num b), mod(Num a, Num b), squareRoot(Num a)
All methods are static so you can call them just like add shown above.
The methods power, divide, mod and squareRoot will throw Runtime Expection when there are certain violations. They are mentioned in comments in Num.java
L1L2.java has example of divide.

all methods mentioned above use same strategy as profesor discussed in class.

Level 3:

Implementation of this level is in L3Driver.java and LP1L3.java. Run file LP1L3.java to execute the program.

Level 4:

Implementation of this level is in L4Driver.java, ShuntingYard.java and LP1L4.java. Run file LP1L4.java to execute the program.


