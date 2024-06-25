# Problem Statement:
# A mathematical expression containing +,-,*,^, / and parenthesis will be provided. Read in the expression, then evaluate it.
# Display the result rounded to decimal places.

#Constraints
#All numeric values are <= 999.

#Sample Input 1 -> 5+50*3/20 + (19*2)/7
# Sample Output 1 -> 17.929

#Sample Input 2 -> -105+50*3/20 + (19^2)/7
# Sample Output 2 -> -45.929

#Sample Input 3 -> (-105.5*7+50*3)/20 + (19^2)/7
# Sample Output 3 -> 22.146

# Solution:
read line;
printf "%.3f" $(echo "scale = 4; $line" | bc);

