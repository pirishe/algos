# Given two arrays, write a function to compute their intersection.
#
# Example 1:
# Input: nums1 = [1,2,2,1], nums2 = [2,2]
# Output: [2]
#
# Example 2:
# Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
# Output: [9,4]
#
# Note:
#
# Each element in the result must be unique.
# The result can be in any order.

class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> hashset1(nums1.begin(), nums1.end());
        unordered_set<int> retHashset;
        for (const auto& itr: nums2) {
            if (hashset1.count((int) itr) > 0) {
                retHashset.insert((int) itr);
            }
        }
        vector<int> ret(retHashset.begin(), retHashset.end());
        return ret;
    }
};
