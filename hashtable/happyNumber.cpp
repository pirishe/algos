# Write an algorithm to determine if a number is "happy".
# A happy number is a number defined by the following process: Starting with any positive integer,
# replace the number by the sum of the squares of its digits, and repeat the process until the number
# equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those
# numbers for which this process ends in 1 are happy numbers.
#
# Example: 
#
# Input: 19
# Output: true
# Explanation: 
# 1^2 + 9^2 = 82
# 8^2 + 2^2 = 68
# 6^2 + 8^2 = 100
# 1^2 + 0^2 + 0^2 = 1

bool isHappy(int n) {
    int procnum = n;
    std::unordered_set<int> sumSquaresSet;
    sumSquaresSet.insert(procnum);
    for (;;) {
        std::vector<int> digits;
        do {
            int digit = procnum % 10;
            digits.push_back(digit);
            procnum -= digit;
            procnum /= 10;
        } while (procnum > 0);
        int sumSquares = std::inner_product(digits.begin(), digits.end(), digits.begin(), 0);
        if (sumSquares == 1) {
            return true;
        }
        if (sumSquaresSet.count(sumSquares) > 0) {
            return false;
        }
        sumSquaresSet.insert(sumSquares);
        procnum = sumSquares;
    }
}
