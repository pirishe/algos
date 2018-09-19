# Given an array of integers, return indices of the two numbers such that they add up to a specific target.
# You may assume that each input would have exactly one solution, and you may not use the same element twice.
#
# Example:
# Given nums = [2, 7, 11, 15], target = 9,
# Because nums[0] + nums[1] = 2 + 7 = 9,
# return [0, 1].

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> hashmap;
        for (int index = 0; index < nums.size(); index++)
        {
            hashmap[target - nums[index]] = index;
        }
        for (int index = 0; index < nums.size(); index++)
        {
            if (hashmap.count(nums[index]) > 0 && hashmap[nums[index]] != index)
            {
                vector<int> ret = {index, hashmap[nums[index]]};
                return ret;
            }
        }
        return {};
    }
};
