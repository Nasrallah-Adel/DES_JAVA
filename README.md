# DES_JAVA

<html>
<head>

</head>

<body bgcolor="white">
<h2 align=center>The DES Algorithm Illustrated</h2>
<h3 align=center><i>by J. Orlin Grabbe</i></h3>
<center><table border=0 width=80%><tr><td>
<p>
      The  DES (Data Encryption Standard) algorithm  is  the
most  widely  used encryption algorithm in the  world.   For
many years, and among many people, "secret code making"  and
DES  have been synonymous.  And despite the recent  coup  by
the  Electronic Frontier Foundation in creating  a  $220,000
machine to crack DES-encrypted messages, DES will live on in
government  and  banking for years to come through  a  life-
extending version called "triple-DES."
<p>
      How  does DES work?  This article explains the various
steps involved in DES-encryption, illustrating each step  by
means of a simple example.  Since the creation of DES,  many
other  algorithms (recipes for changing data)  have  emerged
which  are based on design principles similar to DES.   Once
you understand the basic transformations that take place  in
DES,  you will find it easy to follow the steps involved  in
these more recent algorithms.
<p>
      But  first a bit of history of how DES came  about  is
appropriate, as well as a look toward the future.
<p>
<h3 align=center>The National Bureau of Standards Coaxes the Genie from the Bottle</h3>
<p>
     On May 15, 1973, during the reign of Richard Nixon, the
National Bureau of Standards (NBS) published a notice in the
Federal  Register  soliciting  proposals  for  cryptographic
algorithms to protect data during transmission and  storage.
The notice explained why encryption was an important issue.
<blockquote>
<p>
     Over   the   last  decade,  there  has   been   an
     accelerating  increase  in the  accumulations  and
     communication  of  digital  data  by   government,
     industry and by other organizations in the private
     sector.   The  contents of these communicated  and
     stored  data  often  have very  significant  value
     and/or sensitivity.  It is now common to find data
     transmissions which constitute funds transfers  of
     several  million  dollars,  purchase  or  sale  of
     securities,  warrants for arrests  or  arrest  and
     conviction records being communicated between  law
     enforcement  agencies,  airline  reservations  and
     ticketing  representing investment and value  both
     to  the  airline  and passengers, and  health  and
     patient  care records transmitted among physicians
     and treatment centers.
<p>     
     The  increasing  volume, value and confidentiality
     of  these records regularly transmitted and stored
     by  commercial and government agencies has led  to
     heightened  recognition  and  concern  over  their
     exposures  to unauthorized access and  use.   This
     misuse can be in the form of theft or defalcations
     of  data  records  representing  money,  malicious
     modification  of  business  inventories   or   the
     interception    and   misuse    of    confidential
     information about people.  The need for protection
     is then apparent and urgent.
<p>     
     It  is recognized that encryption (otherwise known
     as     scrambling,    enciphering    or    privacy
     transformation)  represents  the  only  means   of
     protecting  such  data during transmission  and  a
     useful  means  of protecting the content  of  data
     stored  on various media, providing encryption  of
     adequate strength can be devised and validated and
     is inherently integrable into system architecture.
     The National Bureau of Standards solicits proposed
     techniques   and  algorithms  for  computer   data
     encryption.   The Bureau also solicits recommended
     techniques   for  implementing  the  cryptographic
     function:    for   generating,   evaluating,   and
     protecting  cryptographic  keys;  for  maintaining
     files  encoded  under expiring  keys;  for  making
     partial  updates  to encrypted  files;  and  mixed
     clear  and  encrypted  data to  permit  labelling,
     polling, routing, etc.  The Bureau in its role for
     establishing  standards and aiding government  and
     industry in assessing technology, will arrange for
     the  evaluation of protection methods in order  to
     prepare guidelines.
</blockquote>
<p>
      NBS  waited for the responses to come in.  It received
none  until  August  6,  1974,  three  days  before  Nixon's
resignation,  when  IBM submitted a candidate  that  it  had
developed   internally  under  the  name   LUCIFER.    After
evaluating  the  algorithm with the  help  of  the  National
Security Agency (NSA), the NBS adopted a modification of the
LUCIFER algorithm as the new Data Encryption Standard  (DES)
on July 15, 1977.
<p>
      DES was quickly adopted for non-digital media, such as
voice-grade  public  telephone lines.  Within  a  couple  of
years, for example, International Flavors and Fragrances was
using DES to protect its valuable formulas transmitted  over
the  phone ("With Data Encryption, Scents Are Safe at  IFF,"
<i>Computerworld</i> 14, No. 21, 95 (1980).)
<p>
      Meanwhile, the banking industry, which is the  largest
user  of  encryption outside government, adopted  DES  as  a
wholesale  banking  standard.  Standards for  the  wholesale
banking  industry are set by the American National Standards
Institute  (ANSI).  ANSI X3.92, adopted in  1980,  specified
the use of the DES algorithm.
<p>
<h3 align=center>Some Preliminary Examples of DES</h3>
<p>
      DES  works on bits, or binary numbers--the 0s  and  1s
common to digital computers.  Each group of four bits  makes
up  a  hexadecimal, or base 16, number.   Binary  "0001"  is
equal  to the hexadecimal number "1", binary "1000" is equal
to  the  hexadecimal  number "8", "1001"  is  equal  to  the
hexadecimal  number "9", "1010" is equal to the  hexadecimal
number  "A",  and "1111" is equal to the hexadecimal  number
"F".
<p>
      DES  works  by  encrypting groups of 64 message  bits,
which  is  the same as 16 hexadecimal numbers.   To  do  the
encryption,  DES  uses "keys" where are also  <i>apparently</i>  16
hexadecimal  numbers  long,  or  <i>apparently</i>  64  bits  long.
However,  every 8th key bit is ignored in the DES algorithm,
so  that  the  effective key size is 56 bits.  But,  in  any
case,  64  bits (16 hexadecimal digits) is the round  number
upon which DES is organized.
<p>
       For   example,  if  we  take  the  plaintext  message
"8787878787878787",  and  encrypt  it  with  the   DES   key
"0E329232EA6D0D73",   we   end  up   with   the   ciphertext
"0000000000000000".  If the ciphertext is decrypted with the
same  secret DES key "0E329232EA6D0D73", the result  is  the
original plaintext "8787878787878787".
<p>
      This example is neat and orderly because our plaintext
was  exactly  64 bits long.  The same would be true  if  the
plaintext  happened to be a multiple of 64 bits.   But  most
messages will not fall into this category.  They will not be
an  exact multiple of 64 bits (that is, an exact multiple of
16 hexadecimal numbers).
<p>
      For  example, take the message "Your lips are smoother
than  vaseline".   This plaintext message is  38  bytes  (76
hexadecimal  digits) long.  So this message must  be  padded
with  some  extra bytes at the tail end for the  encryption.
Once  the encrypted message has been decrypted, these  extra
bytes  are  thrown  away.  There are, of  course,  different
padding schemes--different ways to add extra bytes.  Here we
will just add 0s at the end, so that the total message is  a
multiple of 8 bytes (or 16 hexadecimal digits, or 64 bits).
<p>
      The  plaintext  message "Your lips are  smoother  than
vaseline" is, in hexadecimal,
<p>
"596F7572206C6970 732061726520736D 6F6F746865722074 68616E2076617365 6C696E650D0A".
<p>
(Note  here  that the first 72 hexadecimal digits  represent
the  English message, while "0D" is hexadecimal for Carriage
Return, and "0A" is hexadecimal for Line Feed, showing  that
the  message file has terminated.)  We then pad this message
with  some  0s on the end, to get a total of 80  hexadecimal
digits:
<p>                              
"596F7572206C6970 732061726520736D 6F6F746865722074 68616E2076617365 6C696E650D0A0000".
<p>
If  we  then  encrypt  this plaintext message  64  bits  (16
hexadecimal  digits)  at  a time, using  the  same  DES  key
"0E329232EA6D0D73" as before, we get the ciphertext:
<p>
"C0999FDDE378D7ED 727DA00BCA5A84EE 47F269A4D6438190 D9D52F78F5358499 828AC9B453E0E653".
<p>                              
This  is  the secret code that can be transmitted or stored.
Decrypting  the  ciphertext restores  the  original  message
"Your  lips  are smoother than vaseline".  (Think  how  much
better  off Bill Clinton would be today, if Monica  Lewinsky
had used encryption on her Pentagon computer!)
<p>
<h3 align=center>How DES Works in Detail</h3>
<p>
     DES is a <b><i>block cipher</i></b>--meaning it operates on plaintext
blocks  of  a  given  size (64-bits) and returns  ciphertext
blocks  of the same size.  Thus DES results in a <b><i>permutation</i></b>
among  the  2^64 (read this as: "2 to the 64th power") possible arrangements of 64 bits,  each  of
which may be either 0 or 1. Each block of 64 bits is divided
into  two blocks of 32 bits each, a left half block <b>L</b> and  a
right  half  <b>R</b>.   (This  division is only  used  in  certain
operations.)
<p>
      <b>Example:</b>   Let  <b>M</b>  be  the  plain  text  message  <b>M</b>  =
0123456789ABCDEF,  where  <b>M</b>  is  in  hexadecimal  (base  16)
format.   Rewriting <b>M</b> in binary format, we  get  the  64-bit
block of text:
<p>
<b>M</b>  =  0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111<br>
<b>L</b>  =  0000 0001 0010 0011 0100 0101 0110 0111<br>
<b>R</b>  =  1000 1001 1010 1011 1100 1101 1110 1111
<p>
The first bit of <b>M</b> is "0".  The last bit is "1".  We read
from left to right.
<p>
     DES operates on the 64-bit blocks using <i>key</i> sizes of 56-
bits.   The keys are actually stored as being 64 bits  long,
but every 8th bit in the key is not used (i.e. bits numbered
8,  16,  24,  32,  40,  48, 56, and 64).  However,  we  will
nevertheless  number the bits from 1 to 64,  going  left  to
right, in the following calculations.  But, as you will see,
the  eight bits just mentioned get eliminated when we create
subkeys.
<p>
       <b>Example:</b>   Let  <b>K</b>  be  the  hexadecimal   key   <b>K</b>   =
133457799BBCDFF1.  This gives us as the binary key  (setting
1  = 0001, 3 = 0011, etc., and grouping together every eight
bits, of which the last one in each group will be unused):
<p>
<b>K</b> =  00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001
<p>
     The DES algorithm uses the following steps:
<p>
<h2 align=center>Step 1:  Create 16 subkeys, each of which is 48-bits long.</h2>
<p>
      The  64-bit key is permuted according to the following
table, <b>PC-1</b>.  Since the first entry in the table  is  "57",
this  means that the 57th bit of the original key <b>K</b>  becomes
the  first bit of the permuted key <b>K</b>+.  The 49th bit of  the
original  key  becomes the second bit of the  permuted  key.
The  4th  bit  of the original key is the last  bit  of  the
permuted key.  Note only 56 bits of the original key  appear
in the permuted key.
<p>
<pre>
                            <b>PC-1</b>

              57   49    41   33    25    17    9
               1   58    50   42    34    26   18
              10    2    59   51    43    35   27
              19   11     3   60    52    44   36
              63   55    47   39    31    23   15
               7   62    54   46    38    30   22
              14    6    61   53    45    37   29
              21   13     5   28    20    12    4
</pre>
<p>
     <b>Example:</b>  From the original 64-bit key
<p>
<b>K</b> =  00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001
<p>
we get the 56-bit permutation
<p>
<b>K</b>+ = 1111000 0110011 0010101 0101111 0101010 1011001 1001111 0001111
<p>
     Next, split this key into left and right halves, <b><i>C<sub>0</sub></i></b> and
<b><i>D<sub>0</sub></i></b>,  where each half has 28 bits.
<p>
     <b>Example:</b>  From the permuted key <b>K</b>+, we get
<p>
<b><i>C<sub>0</sub></i></b> = 1111000 0110011 0010101 0101111 <br>
<b><i>D<sub>0</sub></i></b> = 0101010 1011001 1001111 0001111
<p>
     With <b><i>C<sub>0</sub></i></b> and <b><i>D<sub>0</sub></i></b> defined, we now create sixteen blocks <b><i>C<sub>n</sub></i></b>
and  <b><i>D<sub>n</sub></i></b>,  1<=<b><i>n</i></b><=16.  Each pair of blocks <b><i>C<sub>n</sub></i></b> and <b><i>D<sub>n</sub></i></b> is formed
from the previous pair <b><i>C<sub>n-1</sub></i></b> and <b><i>D<sub>n-1</sub></i></b>, respectively, for <b><i>n</i></b>  =
1, 2, ..., 16, using the following schedule of "left shifts"
of  the  previous block.  To do a left shift, move each  bit
one  place to the left, except for the first bit,  which  is
cycled to the end of the block.
<p>
<pre>
                     Iteration     Number of
                      Number      Left Shifts

                          1          1
                          2          1
                          3          2
                          4          2
                          5          2
                          6          2
                          7          2
                          8          2
                          9          1
                         10          2
                         11          2
                         12          2
                         13          2
                         14          2
                         15          2
                         16          1
</pre>
<p>
This means, for example, <b><i>C<sub>3</sub></i></b> and <b><i>D<sub>3</sub></i></b> are obtained from <b><i>C<sub>2</sub></i></b>  and
<b><i>D<sub>2</sub></i></b>,  respectively, by two left shifts, and <b><i>C<sub>16</sub></i></b> and  <b><i>D<sub>16</sub></i></b>  are
obtained from <b><i>C<sub>15</sub></i></b> and <b><i>D<sub>15</sub></i></b>, respectively, by one left  shift.
In  all cases, by a single left shift is meant a rotation of
the bits one place to the left, so that after one left shift
the  bits  in  the  28  positions are  the  bits  that  were
previously in positions 2, 3,..., 28, 1.
<p>
     <b>Example:</b>  From original pair pair <b><i>C<sub>0</sub></i></b> and <b><i>D<sub>0</sub></i></b>  we obtain:
<p>
<b><i>C<sub>0</sub></i></b> = 1111000011001100101010101111<br>
<b><i>D<sub>0</sub></i></b> = 0101010101100110011110001111
<p>
<b><i>C<sub>1</sub></i></b> = 1110000110011001010101011111<br>
<b><i>D<sub>1</sub></i></b> = 1010101011001100111100011110
<p>
<b><i>C<sub>2</sub></i></b> = 1100001100110010101010111111<br>
<b><i>D<sub>2</sub></i></b> = 0101010110011001111000111101
<p>
<b><i>C<sub>3</sub></i></b> = 0000110011001010101011111111<br>
<b><i>D<sub>3</sub></i></b> = 0101011001100111100011110101
<p>
<b><i>C<sub>4</sub></i></b> = 0011001100101010101111111100<br>
<b><i>D<sub>4</sub></i></b> = 0101100110011110001111010101
<p>
<b><i>C<sub>5</sub></i></b> = 1100110010101010111111110000<br>
<b><i>D<sub>5</sub></i></b> = 0110011001111000111101010101
<p>
<b><i>C<sub>6</sub></i></b> = 0011001010101011111111000011<br>
<b><i>D<sub>6</sub></i></b> = 1001100111100011110101010101
<p>
<b><i>C<sub>7</sub></i></b> = 1100101010101111111100001100<br>
<b><i>D<sub>7</sub></i></b> = 0110011110001111010101010110
<p>
<b><i>C<sub>8</sub></i></b> = 0010101010111111110000110011<br>
<b><i>D<sub>8</sub></i></b> = 1001111000111101010101011001
<p>
<b><i>C<sub>9</sub></i></b> = 0101010101111111100001100110<br>
<b><i>D<sub>9</sub></i></b> = 0011110001111010101010110011
<p>
<b><i>C<sub>10</sub></i></b> = 0101010111111110000110011001<br>
<b><i>D<sub>10</sub></i></b> = 1111000111101010101011001100
<p>
<b><i>C<sub>11</sub></i></b> = 0101011111111000011001100101<br>
<b><i>D<sub>11</sub></i></b> = 1100011110101010101100110011
<p>
<b><i>C<sub>12</sub></i></b> = 0101111111100001100110010101<br>
<b><i>D<sub>12</sub></i></b> = 0001111010101010110011001111
<p>
<b><i>C<sub>13</sub></i></b> = 0111111110000110011001010101<br>
<b><i>D<sub>13</sub></i></b> = 0111101010101011001100111100
<p>
<b><i>C<sub>14</sub></i></b> = 1111111000011001100101010101<br>
<b><i>D<sub>14</sub></i></b> = 1110101010101100110011110001
<p>
<b><i>C<sub>15</sub></i></b> = 1111100001100110010101010111<br>
<b><i>D<sub>15</sub></i></b> = 1010101010110011001111000111
<p>
<b><i>C<sub>16</sub></i></b> = 1111000011001100101010101111<br>
<b><i>D<sub>16</sub></i></b> = 0101010101100110011110001111
<p>
     We now form the keys  <b><i>K<sub>n</sub></i></b>, for 1<=<b><i>n</i></b><=16, by applying the
following  permutation  table to each  of  the  concatenated
pairs <b><i>C<sub>n</sub>D<sub>n</sub></i></b>.  Each pair has 56 bits, but <b>PC-2</b> only uses 48 of
these.
<p>
<pre>
                              <b>PC-2</b>

                 14    17   11    24     1    5
                  3    28   15     6    21   10
                 23    19   12     4    26    8
                 16     7   27    20    13    2
                 41    52   31    37    47   55
                 30    40   51    45    33   48
                 44    49   39    56    34   53
                 46    42   50    36    29   32
</pre>
<p>
Therefore, the first bit of <b><i>K<sub>n</sub></i></b> is the 14th bit of <b><i>C<sub>n</sub>D<sub>n</sub></i></b>,  the
second bit the 17th, and so on, ending with the 48th bit  of
<b><i>K<sub>n</sub></i></b> being the 32th bit of <b><i>C<sub>n</sub>D<sub>n</sub></i></b>.
<p>
     <b>Example:</b>  For the first key we have

<b><i>C<sub>1</sub>D<sub>1</sub></i></b> = 1110000 1100110 0101010 1011111 1010101 0110011 0011110 0011110
<p>
which, after we apply the permutation <b>PC-2</b>, becomes
<p>
<b><i>K<sub>1</sub></i></b> =   000110 110000 001011 101111 111111 000111 000001 110010
<p>
For the other keys we have
<p>
<b><i>K<sub>2</sub></i></b> =  011110 011010 111011 011001 110110 111100 100111 100101<br>
<b><i>K<sub>3</sub></i></b> =  010101 011111 110010 001010 010000 101100 111110 011001<br>
<b><i>K<sub>4</sub></i></b> =  011100 101010 110111 010110 110110 110011 010100 011101<br>
<b><i>K<sub>5</sub></i></b> =  011111 001110 110000 000111 111010 110101 001110 101000<br>
<b><i>K<sub>6</sub></i></b> =  011000 111010 010100 111110 010100 000111 101100 101111<br>
<b><i>K<sub>7</sub></i></b> =  111011 001000 010010 110111 111101 100001 100010 111100<br>
<b><i>K<sub>8</sub></i></b> =  111101 111000 101000 111010 110000 010011 101111 111011<br>
<b><i>K<sub>9</sub></i></b> =  111000 001101 101111 101011 111011 011110 011110 000001<br>
<b><i>K<sub>10</sub></i></b> = 101100 011111 001101 000111 101110 100100 011001 001111<br>
<b><i>K<sub>11</sub></i></b> = 001000 010101 111111 010011 110111 101101 001110 000110<br>
<b><i>K<sub>12</sub></i></b> = 011101 010111 000111 110101 100101 000110 011111 101001<br>
<b><i>K<sub>13</sub></i></b> = 100101 111100 010111 010001 111110 101011 101001 000001<br>
<b><i>K<sub>14</sub></i></b> = 010111 110100 001110 110111 111100 101110 011100 111010<br>
<b><i>K<sub>15</sub></i></b> = 101111 111001 000110 001101 001111 010011 111100 001010<br>
<b><i>K<sub>16</sub></i></b> = 110010 110011 110110 001011 000011 100001 011111 110101<br>
<p>
So much for the subkeys.  Now we look at the message itself.
<p>
<h2 align=center>Step 2:  Encode each 64-bit block of data.</h2>
<p>
      There  is an <i>initial permutation</i> <b>IP</b> of the 64 bits  of
the  message data <b>M</b>.  This rearranges the bits according  to
the following table, where the entries in the table show the
new  arrangement of the bits from their initial order.   The
58th bit of <b>M</b> becomes the first bit of  <b>IP</b>. The 50th bit  of
<b>M</b>  becomes the second bit of <b>IP</b>.  The 7th bit of  <b>M</b>  is  the
last bit of <b>IP</b>.
<p>
<pre>
                             <b>IP</b>

            58    50   42    34    26   18    10    2
            60    52   44    36    28   20    12    4
            62    54   46    38    30   22    14    6
            64    56   48    40    32   24    16    8
            57    49   41    33    25   17     9    1
            59    51   43    35    27   19    11    3
            61    53   45    37    29   21    13    5
            63    55   47    39    31   23    15    7
</pre>
<p>
     <b>Example:</b>  Applying the initial permutation to the block
of text <b>M</b>, given previously, we get
<p>
<b>M</b>  = 0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111<br>
<b>IP</b> = 1100 1100 0000 0000 1100 1100 1111 1111 1111 0000 1010 1010 1111 0000 1010 1010
<p>
Here  the 58th bit of <b>M</b> is "1", which becomes the first  bit
of  <b>IP</b>.   The 50th bit of <b>M</b> is "1", which becomes the second
bit  of <b>IP</b>.  The 7th bit of <b>M</b> is "0", which becomes the last
bit of <b>IP</b>.
<p>
      Next divide the permuted block <b>IP</b> into a left half  <b><i>L<sub>0</sub></i></b>
of 32 bits, and a right half <b><i>R<sub>0</sub></i></b> of 32 bits.
<p>
     <b>Example:</b>  From <b>IP</b>, we get <b><i>L<sub>0</sub></i></b> and <b><i>R<sub>0</sub></i></b>
<p>                              
<b><i>L<sub>0</sub></i></b> = 1100 1100 0000 0000 1100 1100 1111 1111 <br>
<b><i>R<sub>0</sub></i></b> = 1111 0000 1010 1010 1111 0000 1010 1010
<p>
     We now proceed through 16 iterations, for 1<=<b><i>n</i></b><=16, using
a  function <b><i>f</i></b> which operates on two blocks--a data block  of
32  bits and a key <b><i>K<sub>n</sub></i></b> of 48 bits--to produce a block  of  32
bits.   <b>Let + denote  XOR  addition,  (bit-by-bit  addition
modulo 2)</b>.  Then for <b>n</b> going from 1 to 16 we calculate
<p>
<blockquote>
                         <b><i>L<sub>n</sub></i></b> = <b><i>R<sub>n-1</sub></i></b>               <br>
                         <b><i>R<sub>n</sub></i></b> = <b><i>L<sub>n-1</sub></i></b> + <b><i>f</i></b>(<b><i>R<sub>n-1</sub></i></b>,<b><i>K<sub>n</sub></i></b>)
</blockquote>                         
<p>
This results in a final block, for <b><i>n</i></b> = 16, of  <b><i>L<sub>16</sub>R<sub>16</sub></i></b>.  That
is,  in  each  iteration, we take the right 32 bits  of  the
previous  result  and  make them the left  32  bits  of  the
current step.  For the right 32 bits in the current step, we
XOR  the  left  32  bits  of  the  previous  step  with  the
calculation <b><i>f</i></b> .
<p>
     <b>Example:</b>  For <b><i>n</i></b> = 1, we have
<p>
<b><i>K<sub>1</sub></i></b> =   000110 110000 001011 101111 111111 000111 000001 110010 <br>
<b><i>L<sub>1</sub></i></b> = <b><i>R<sub>0</sub></i></b> = 1111 0000 1010 1010 1111 0000 1010 1010              <br>
<b><i>R<sub>1</sub></i></b> = <b><i>L<sub>0</sub></i></b> + <b><i>f</i></b>(<b><i>R<sub>0</sub></i></b>,<b><i>K<sub>1</sub></i></b>)
<p>
      It  remains  to explain how the function <b><i>f</i></b>  works.  To
calculate <b><i>f</i></b>, we first expand each block <b><i>R<sub>n-1</sub></i></b> from 32 bits to
48  bits.   This  is  done by using a selection  table  that
repeats  some of the bits in <b><i>R<sub>n-1</sub></i></b> .  We'll call the  use  of
this selection table the function <b>E</b>.  Thus <b>E</b>(<b><i>R<sub>n-1</sub></i></b>) has a 32
bit input block, and a 48 bit output block.
<p>
      Let  <b>E</b> be such that the 48 bits of its output, written
as  8  blocks of 6 bits each, are obtained by selecting  the
bits  in  its  inputs in order according  to  the  following
table:
<p>
<pre>
                    <b>E BIT-SELECTION TABLE</b>

                 32     1    2     3     4    5
                  4     5    6     7     8    9
                  8     9   10    11    12   13
                 12    13   14    15    16   17
                 16    17   18    19    20   21
                 20    21   22    23    24   25
                 24    25   26    27    28   29
                 28    29   30    31    32    1
</b></pre>
<p>
Thus  the  first  three  bits of <b>E</b>(<b><i>R<sub>n-1</sub></i></b>)  are  the  bits  in
positions 32, 1 and 2 of <b><i>R<sub>n-1</sub></i></b> while the last 2 bits of <b>E</b>(<b><i>R<sub>n-1</sub></i></b>) are the bits in positions 32 and 1.
<p>
   <b>Example:</b>   We  calculate  <b>E</b>(<b><i>R<sub>0</sub></i></b>)  from  <b><i>R<sub>0</sub></i></b>   as follows:
<p>
<b><i>R<sub>0</sub></i></b>      = 1111 0000 1010 1010 1111 0000 1010 1010        <br>
<b>E</b>(<b><i>R<sub>0</sub></i></b>)  =  011110 100001 010101 010101  011110  100001 010101 010101
<p>
(Note  that  each block of 4 original bits  has  been
expanded to a block of 6 output bits.)
<p>
      Next  in the  <b><i>f</i></b> calculation, we XOR the  output
<b>E</b>(<b><i>R<sub>n-1</sub></i></b>) with the key <b><i>K<sub>n</sub></i></b>:
<p><center>
                    <b><i>K<sub>n</sub></i></b>   + <b>E</b>(<b><i>R<sub>n-1</sub></i></b>).
</center><p>
     <b>Example:</b>  For <b><i>K<sub>1</sub></i></b> , <b>E</b>(<b><i>R<sub>0</sub></i></b>), we have
<p>
<b><i>K<sub>1</sub></i></b>   =  000110 110000 001011 101111 111111 000111 000001 110010    <br>
<b>E</b>(<b><i>R<sub>0</sub></i></b>)   =  011110 100001 010101 010101 011110 100001 010101 010101 <br>
<b><i>K<sub>1</sub></i></b>+<b>E</b>(<b><i>R<sub>0</sub></i></b>) =  011000 010001 011110 111010 100001 100110 010100 100111.
<p>
      We have not yet finished calculating the function  <b><i>f</i></b> .
To  this  point we have expanded <b><i>R<sub>n-1</sub></i></b>  from 32  bits  to  48
bits,  using the selection table, and XORed the result  with
the  key <b><i>K<sub>n</sub></i></b> .  We now have 48 bits, or eight groups  of  six
bits.   We now do something strange with each group  of  six
bits:  we use them as addresses in tables called "<b>S  boxes</b>".
Each  group  of  six  bits will give  us  an  address  in  a
different  <b>S</b> box. Located at that address will be  a  4  bit
number. This 4 bit number will replace the original 6  bits.
The  net  result  is that the eight groups  of  6  bits  are
transformed  into eight groups of 4 bits (the 4-bit  outputs
from the <b>S</b> boxes) for 32 bits total.
<p>
      Write the previous result, which is 48 bits, in
the form:
<p><center>
 <b><i>K<sub>n</sub></i></b> + <b>E</b>(<b><i>R<sub>n-1</sub></i></b>) =<b><i>B<sub>1</sub>B<sub>2</sub>B<sub>3</sub>B<sub>4</sub>B<sub>5</sub>B<sub>6</sub>B<sub>7</sub>B<sub>8</sub></i></b>,
</center><p>
where  each  <b><i>B<sub>i</sub></i></b>  is  a group of  six  bits.   We  now calculate
<p><center>
<b><i>S<sub>1</sub>(B<sub>1</sub>)S<sub>2</sub>(B<sub>2</sub>)S<sub>3</sub>(B<sub>3</sub>)S<sub>4</sub>(B<sub>4</sub>)S<sub>5</sub>(B<sub>5</sub>)S<sub>6</sub>(B<sub>6</sub>)S<sub>7</sub>(B<sub>7</sub>)S<sub>8</sub>(B<sub>8</sub>)</i></b>
</center><p>
<p>
where  <b><i>S<sub>i</sub>(B<sub>i</sub>)</i></b> referres to the output of the <b><i>i</i></b>-th <b>S</b>
box.
<p>
      To repeat, each of the functions <b><i>S1, S2,..., S8</i></b>, takes
a  6-bit  block as input and yields a 4-bit block as output.
The table to determine <b><i>S<sub>1</sub></i></b> is shown and explained below:
<p>
<pre>
<b>
                             S1

                        Column Number
Row
No.    0  1   2  3   4  5   6  7   8  9  10 11  12 13  14 15

  0   14  4  13  1   2 15  11  8   3 10   6 12   5  9   0  7
  1    0 15   7  4  14  2  13  1  10  6  12 11   9  5   3  8
  2    4  1  14  8  13  6   2 11  15 12   9  7   3 10   5  0
  3   15 12   8  2   4  9   1  7   5 11   3 14  10  0   6 13
</b></pre>
<p>
If <b><i>S<sub>1</sub></i></b> is the function defined in this table and <b><i>B</i></b> is a block
of  6  bits, then <b><i>S<sub>1</sub>(B)</i></b> is determined as follows:  The  first
and  last  bits  of <b><i>B</i></b> represent in base 2 a  number  in  the
decimal range 0 to 3 (or binary 00 to 11).  Let that  number
be  <b><i>i</i></b>.   The middle 4 bits of <b><i>B</i></b> represent in base 2 a number
in  the  decimal range 0 to 15 (binary 0000 to  1111).   Let
that number be <b><i>j</i></b>.  Look up in the table the number in the <b><i>i</i></b>-th row and <b><i>j</i></b>-th column.  It is a number in the range 0 to 15
and is uniquely represented by a 4 bit block.  That block is
the  output  <b><i>S<sub>1</sub>(B)</i></b> of <b><i>S<sub>1</sub></i></b> for the input <b><i>B</i></b>.  For example,  for
input block <b><i>B</i></b> = 011011 the first bit is "0" and the last bit
"1"  giving 01 as the row.  This is row 1.  The middle  four
bits  are "1101".  This is the binary equivalent of  decimal
13, so the column is column number 13.  In row 1, column  13
appears 5.  This determines the output; 5 is binary 0101, so
that the output is 0101.   Hence <b><i>S<sub>1</sub></i></b>(011011) = 0101.
<p>
      The tables defining the functions <b><i>S<sub>1</sub>,...,S<sub>8</sub></i></b> are
the following:
<p>
<pre>
                             <b>S1</b>

     14  4  13  1   2 15  11  8   3 10   6 12   5  9   0  7
      0 15   7  4  14  2  13  1  10  6  12 11   9  5   3  8
      4  1  14  8  13  6   2 11  15 12   9  7   3 10   5  0
     15 12   8  2   4  9   1  7   5 11   3 14  10  0   6 13

                             <b>S2</b>

     15  1   8 14   6 11   3  4   9  7   2 13  12  0   5 10
      3 13   4  7  15  2   8 14  12  0   1 10   6  9  11  5
      0 14   7 11  10  4  13  1   5  8  12  6   9  3   2 15
     13  8  10  1   3 15   4  2  11  6   7 12   0  5  14  9

                             <b>S3</b>

     10  0   9 14   6  3  15  5   1 13  12  7  11  4   2  8
     13  7   0  9   3  4   6 10   2  8   5 14  12 11  15  1
     13  6   4  9   8 15   3  0  11  1   2 12   5 10  14  7
      1 10  13  0   6  9   8  7   4 15  14  3  11  5   2 12

                             <b>S4</b>

      7 13  14  3   0  6   9 10   1  2   8  5  11 12   4 15
     13  8  11  5   6 15   0  3   4  7   2 12   1 10  14  9
     10  6   9  0  12 11   7 13  15  1   3 14   5  2   8  4
      3 15   0  6  10  1  13  8   9  4   5 11  12  7   2 14

                             <b>S5</b>

      2 12   4  1   7 10  11  6   8  5   3 15  13  0  14  9
     14 11   2 12   4  7  13  1   5  0  15 10   3  9   8  6
      4  2   1 11  10 13   7  8  15  9  12  5   6  3   0 14
     11  8  12  7   1 14   2 13   6 15   0  9  10  4   5  3

                             <b>S6</b>

     12  1  10 15   9  2   6  8   0 13   3  4  14  7   5 11
     10 15   4  2   7 12   9  5   6  1  13 14   0 11   3  8
      9 14  15  5   2  8  12  3   7  0   4 10   1 13  11  6
      4  3   2 12   9  5  15 10  11 14   1  7   6  0   8 13

                             <b>S7</b>

      4 11   2 14  15  0   8 13   3 12   9  7   5 10   6  1
     13  0  11  7   4  9   1 10  14  3   5 12   2 15   8  6
      1  4  11 13  12  3   7 14  10 15   6  8   0  5   9  2
      6 11  13  8   1  4  10  7   9  5   0 15  14  2   3 12

                             <b>S8</b>

     13  2   8  4   6 15  11  1  10  9   3 14   5  0  12  7
      1 15  13  8  10  3   7  4  12  5   6 11   0 14   9  2
      7 11   4  1   9 12  14  2   0  6  10 13  15  3   5  8
      2  1  14  7   4 10   8 13  15 12   9  0   3  5   6 11
</pre>
<p>
      <b>Example:</b>  For the first round, we obtain as the
output of the eight <b>S</b> boxes:
<p>
<b><i>K<sub>1</sub></i></b> + <b>E</b>(<b><i>R<sub>0</sub></i></b>) =  011000 010001 011110 111010 100001 100110 010100 100111.
<p>
<b><i>S<sub>1</sub>(B<sub>1</sub>)S<sub>2</sub>(B<sub>2</sub>)S<sub>3</sub>(B<sub>3</sub>)S<sub>4</sub>(B<sub>4</sub>)S<sub>5</sub>(B<sub>5</sub>)S<sub>6</sub>(B<sub>6</sub>)S<sub>7</sub>(B<sub>7</sub>)S<sub>8</sub>(B<sub>8</sub>)</i></b>
    =      0101      1100      1000   0010     1011     0101     1001     0111
<p>
      The  final stage in the calculation of <b><i>f</i></b> is  to  do  a
permutation <b>P</b> of the <b>S</b>-box output to obtain the final  value
of <b><i>f</i></b>:
<p><center>
               <b><i>f</i></b> = <b>P</b>(<b><i>S<sub>1</sub>(B<sub>1</sub>)S<sub>2</sub>(B<sub>2</sub>)...S<sub>8</sub>(B<sub>8</sub>)</i></b>)
</center><p>
  The  permutation <b>P</b> is defined in the following  table.   <b>P</b>
yields a 32-bit output from a 32-bit input by permuting  the
bits of the input block.
<p>
<pre>
                                <b>P</b>

                         16   7  20  21
                         29  12  28  17
                          1  15  23  26
                          5  18  31  10
                          2   8  24  14
                         32  27   3   9
                         19  13  30   6
                         22  11   4  25
</pre>
<p>
     <b>Example:</b>  From the output of the eight <b>S</b> boxes:
<p><center>
<b><i>S<sub>1</sub>(B<sub>1</sub>)S<sub>2</sub>(B<sub>2</sub>)S<sub>3</sub>(B<sub>3</sub>)S<sub>4</sub>(B<sub>4</sub>)S<sub>5</sub>(B<sub>5</sub>)S<sub>6</sub>(B<sub>6</sub>)S<sub>7</sub>(B<sub>7</sub>)S<sub>8</sub>(B<sub>8</sub>)</i></b> = 0101 1100 1000 0010 1011 0101 1001 0111
</center><p>
we get
<p><center>
<b><i>f</i></b>      =   0010 0011 0100 1010 1010     1001     1011     1011
</center><p>
<b><i>R<sub>1</sub></i></b> = <b><i>L<sub>0</sub></i></b> + <b><i>f</i></b>(<b><i>R<sub>0</sub></i></b> , <b><i>K<sub>1</sub></i></b> )<br>
<blockquote>
           = 1100 1100 0000 0000 1100 1100 1111 1111 <br>
           + 0010 0011 0100 1010 1010 1001 1011 1011 <br>
           = 1110 1111 0100 1010 0110 0101 0100 0100
</blockquote>
<p>
      In  the next round, we will have <b><i>L<sub>2</sub></i></b> = <b><i>R<sub>1</sub></i></b>, which is the
block we just calculated, and then we must calculate <b><i>R<sub>2</sub></i></b> =<b><i>L<sub>1</sub> +  f(R<sub>1</sub>, K<sub>2</sub>)</i></b>, and so on for 16 rounds.  At the end  of  the
sixteenth round we have the blocks <b><i>L<sub>16</sub></i></b>  and <b><i>R<sub>16</sub></i></b>.   We  then
<b><i>reverse</i></b> the order of the two blocks into the 64-bit block
<p><center>
                    <b><i>R<sub>16</sub>L<sub>16</sub></i></b>
</center><p>
and  apply a final permutation  <b>IP<sup>-1</sup></b>   as defined  by
the following table:
<p>
<pre>
                             <b>IP<sup>-1</sup></b>

            40     8   48    16    56   24    64   32
            39     7   47    15    55   23    63   31
            38     6   46    14    54   22    62   30
            37     5   45    13    53   21    61   29
            36     4   44    12    52   20    60   28
            35     3   43    11    51   19    59   27
            34     2   42    10    50   18    58   26
            33     1   41     9    49   17    57   25
</pre>
<p>
That  is,  the  output of the algorithm has bit  40  of  the
preoutput  block as its first bit, bit 8 as its second  bit,
and  so on, until bit 25 of the preoutput block is the  last
bit of the output.
<p>
      <b>Example:</b>  If we process all 16 blocks using the method
defined previously, we get, on the 16th round,
<p>
<b><i>L<sub>16</sub></i></b> = 0100 0011 0100 0010 0011 0010 0011 0100 <br>
<b><i>R<sub>16</sub></i></b> = 0000 1010 0100 1100 1101 1001 1001 0101
<p>
We  reverse the order of these two blocks  and  apply
the final permutation to
<p>
<b><i>R<sub>16</sub>L<sub>16</sub></i></b> = 00001010 01001100 11011001 10010101 01000011 01000010 00110010 00110100
<p>
<b><i>IP<sup>-1</sup></i></b> = 10000101 11101000 00010011 01010100 00001111 00001010 10110100 00000101
<p>
which in hexadecimal format is
<p>
       85E813540F0AB405.
<p>
This is the encrypted form of <b>M</b> = 0123456789ABCDEF:  namely,
<b>C</b> = 85E813540F0AB405.
<p>
       Decryption  is  simply  the  inverse  of  encryption,
follwing the same steps as above, but reversing the order in
which the subkeys are applied.
<p>
<h3 align=center>DES Modes of Operation</h3>
<p>
     The DES algorithm turns a 64-bit message block <b>M</b> into a
64-bit  cipher block <b>C</b>.  If each 64-bit block  is  encrypted
individually,  then  the  mode  of  encryption   is   called
<b><i>Electronic Code Book</i></b> (ECB) mode.  There are two other  modes
of  DES  encryption,  namely <b><i>Chain Block  Coding</i></b>  (CBC)  and
<b><i>Cipher   Feedback</i></b> (CFB),  which  make  each  cipher   block
dependent  on  all the previous messages blocks  through  an
initial XOR operation.
<p>
<h3 align=center>Cracking DES</h3>
<p>
      Before DES was adopted as a national standard,  during
the  period  NBS  was soliciting comments  on  the  proposed
algorithm,  the creators of public key cryptography,  Martin
Hellman and Whitfield Diffie, registered some objections  to
the  use of DES as an encryption algorithm.  Hellman  wrote:
"Whit  Diffie and I have become concerned that the  proposed
data  encryption  standard, while  probably  secure  against
commercial assault, may be extremely vulnerable to attack by
an  intelligence organization" (letter to NBS,  October  22,
1975).
<p>
     Diffie and Hellman then outlined a "brute force" attack
on  DES.  (By "brute force" is meant that you try as many of
the  2^56 possible keys as you have to before decrypting the
ciphertext   into  a  sensible  plaintext  message.)    They
proposed  a  special  purpose "parallel computer  using  one
million chips to try one million keys each" per second,  and
estimated the cost of such a machine at $20 million.
<p>
      Fast  forward  to 1998.  Under the direction  of  John
Gilmore  of  the  EFF,  a team spent $220,000  and  built  a
machine that can go through the entire 56-bit DES key  space
in  an average of 4.5 days. On July 17, 1998, they announced
they  had  cracked a 56-bit key in 56 hours.  The  computer,
called  Deep Crack, uses 27 boards each containing 64 chips,
and is capable of testing 90 billion keys a second.
<p>
     Despite this, as recently as June 8, 1998, Robert Litt,
principal   associate  deputy  attorney   general   at   the
Department of Justice, denied it was possible for the FBI to
crack  DES:   "Let me put the technical problem in  context:
It  took 14,000 Pentium computers working for four months to
decrypt  a  single message . . . .  We are not just  talking
FBI  and  NSA  [needing  massive computing  power],  we  are
talking about every police department."
<p>
      Responded cryptograpy expert Bruce Schneier:  " . .  .
the  FBI is either incompetent or lying, or both."  Schneier
went  on  to  say:  "The only solution here is  to  pick  an
algorithm  with a longer key; there isn't enough silicon  in
the galaxy or enough time before the sun burns out to brute-
force  triple-DES" (<i>Crypto-Gram</i>, Counterpane Systems, August
15, 1998).
<p>
<h3 align=center>Triple-DES</h3>
<p>
      Triple-DES  is just DES with two 56-bit keys  applied.
Given  a  plaintext message, the first key is used  to  DES-
encrypt  the message.  The second key is used to DES-decrypt
the  encrypted message.  (Since the second key  is  not  the
right key, this decryption just scrambles the data further.)
The twice-scrambled message is then encrypted again with the
first  key  to yield the final ciphertext.  This  three-step
procedure is called triple-DES.
<p>
      Triple-DES is just DES done three times with two  keys
used  in  a particular order.  (Triple-DES can also be  done
with  three  separate keys instead of only two.   In  either
case the resultant key space is about 2^112.)
<p><center><b>
                     General References
</b></center><p>
"Cryptographic Algorithms for Protection of Computer Data
During Transmission and Dormant Storage," <i>Federal Register</i>
<b>38</b>, No. 93 (May 15, 1973).
<p>
<i>Data Encryption Standard</i>, Federal Information Processing
Standard (FIPS) Publication 46, National Bureau of
Standards, U.S. Department of Commerce, Washington D.C.
(January 1977).
<p>
Carl H. Meyer and Stephen M. Matyas, <i>Cryptography: A New
Dimension in Computer Data Security</i>, John Wiley & Sons, New
York, 1982.
<p>
Dorthy Elizabeth Robling Denning, <i>Cryptography and Data
Security</i>, Addison-Wesley Publishing Company, Reading,
Massachusetts, 1982.
<p>
D.W. Davies and W.L. Price, <i>Security for Computer Networks:
An Introduction to Data Security in Teleprocessing and
Electronics Funds Transfer</i>, Second Edition, John Wiley &
Sons, New York, 1984, 1989.
<p>
Miles E. Smid and Dennis K. Branstad, "The Data Encryption
Standard:  Past and Future," in Gustavus J. Simmons, ed.,
<i>Contemporary Cryptography: The Science of Information
Integrity</i>, IEEE Press, 1992.
<p>
Douglas R. Stinson, <i>Cryptography: Theory and Practice</i>, CRC
Press, Boca Raton, 1995.
<p>
Bruce Schneier, <i>Applied Cryptography, Second Edition</i>, John
Wiley & Sons, New York, 1996.
<p>
Alfred J. Menezes, Paul C. van Oorschot, and Scott A.
Vanstone,  <i>Handbook of Applied Cryptography</i>, CRC Press, Boca
Raton, 1997.
<p>
<center>-30-</center>
<p>

This article appeared in <a href="http://zolatimes.com/">Laissez Faire
City Times</a>, Vol 2, No. 28.
<p>
Homepage: http://orlingrabbe.com/         <br>
Laissez Faire City Times: http://zolatimes.com/
</td></tr></table></center>
</body>
</html>
