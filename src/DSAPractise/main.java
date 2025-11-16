package DSAPractise;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        //1) Longest pallindrome problme
//        Scanner sc = new Scanner(System.in);
//        System.out.println("enter the input");
//        LongestPallindrome longestPallindrome = new LongestPallindrome();
//        System.out.println(longestPallindrome.returnMaxLengthPallindrome(sc.next()));
        //2) Word search problem
//        char[][] mat =new char[][] {{'A', 'B', 'C' ,'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        char[][] mat =new char[][] {{'A', 'B', 'C','E'}, {'S', 'F', 'E','S'}, {'A', 'D', 'E','E'}};
//        WordSearch wordSearch = new WordSearch();
//        System.out.println((wordSearch.searchWord(mat,"ABCESEEEFS")));

        //3) SubsetProblem qstn
//        int[] arr = new int[]{3, 34, 4, 12, 5, 2};
//        int sum = 30;
//        SubSetSumProblem subSetSumProblem = new SubSetSumProblem();
//        System.out.println(subSetSumProblem.doSumExist(arr,sum));

        //4) Three sum qstn
//        int[] arr = new int[]{-1,0,1,2,-1,-4};
//        ThreeSum threeSum = new ThreeSum();
//        System.out.println(threeSum.triplets(arr));

        //5) Longest consecutive sum problem
//        int[] arr = new int[] {2,20,4,10,3,4,5};
//        LongestConsecutiveSum longestConsecutiveSum = new LongestConsecutiveSum();
//        System.out.println(longestConsecutiveSum.getMaxLength(arr));

        //6) Buy sell stock
//        int[] arr = new int[]{7,6,4,3,1};
//        BestTimeToBuyAndSellStock buyAndSellStock = new BestTimeToBuyAndSellStock();
//        System.out.println(buyAndSellStock.getMaxProfit(arr));

//        7) LongestSubStringWithoutRepeatingCharacter
//        String s = " ";
//        LongestSubStringWithoutRepeatingCharacter longestSubStringWithoutRepeatingCharacter = new LongestSubStringWithoutRepeatingCharacter();
//        System.out.println(longestSubStringWithoutRepeatingCharacter.getMaxLength(s));

        //8) LongestRepeatingCharacterReplacement
//        String s = "KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF";
//        int k  = 4;
//        LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LongestRepeatingCharacterReplacement();
//        System.out.println(longestRepeatingCharacterReplacement.getMaxLength(s,k));

        //9)Valid Parentheses
//        String s = "]";
//        ValidParantheses validParantheses = new ValidParantheses();
//        System.out.println(validParantheses.isValid(s));

        //10 Find min in rotated sorted array.
//        int[] arr = new int[]{4,5,6,7,0,1,2};
//        MinimumInRotatedSortedArray minimumInRotatedSortedArray = new MinimumInRotatedSortedArray();
//        System.out.println(minimumInRotatedSortedArray.getMinimum(arr));

        //11 find the target in the rotated sorted array
//        int[] arr = new int[]{3,4,5,6,1,2};
//        int target = 1;
//        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
//        System.out.println(search.getResult(arr,target));

        //12 Reverse linked list
//        ListNode head = new ListNode();
//        head.val = 1;
//        ListNode prev = head;
//        int[] arr = new int[]{2,3,4,5};
//        for (Integer i : arr) {
//            ListNode next = new ListNode();
//            next.val = i;
//            prev.next = next;
//            prev = next;
//        }
//        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
//        head = reverseLinkedList.reverse(head);

        //13
//        ListNode list1 = new ListNode();
//        list1.val = 1;
//        ListNode prev = list1;
//        int[] arr = new int[]{2,4};
//        for (Integer i : arr) {
//            ListNode next = new ListNode();
//            next.val = i;
//            prev.next = next;
//            prev = next;
//        }
//
//        ListNode list2 = new ListNode();
//        list2.val = 1;
//        ListNode prev2 = list2;
//        int[] arr2 = new int[]{3,4};
//        for (Integer i : arr2) {
//            ListNode next = new ListNode();
//            next.val = i;
//            prev2.next = next;
//            prev2 = next;
//        }
//        MergeTwoSortedLinkedList mergeTwoSortedLinkedList = new MergeTwoSortedLinkedList();
//       ListNode head =  mergeTwoSortedLinkedList.merge(list1,list2);

        //14 Reorder lined list
//        ListNode head = new ListNode();
//        head.val = 1;
//        ListNode prev = head;
//        int[] arr = new int[]{2,3,4,5,6,7,8,9,11};
//        for (Integer i : arr) {
//            ListNode next = new ListNode();
//            next.val = i;
//            prev.next = next;
//            prev = next;
//        }
//
//        ReorderList reorderList = new ReorderList();
//        reorderList.reorder(head);

        //15 Remove nth node from the back of the list
//        ListNode head = new ListNode();
//        head.val = 1;
//        ListNode prev = head;
//        int[] arr = new int[]{2,3,4,5,6,7,8,9};
//        for (Integer i : arr) {
//            ListNode next = new ListNode();
//            next.val = i;
//            prev.next = next;
//            prev = next;
//        }
//        RemoveNthNodeFromEnd removeNthNodeFromEnd = new RemoveNthNodeFromEnd();
//        ListNode myHead = removeNthNodeFromEnd.remove(head,1);
//        while(myHead !=null) {
//            System.out.print(myHead.val+" ");
//            myHead = myHead.next;
//        }

        //16 Combination sum
//        int[] arr = new int[]{2,3,6,7};
//        CombinationSum combinationSum = new CombinationSum();
//        System.out.println(combinationSum.getCombination(arr,7));

        //17
//        int[] arr =  new int[]{3,4};
//        int k  = 2;
//        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
//        System.out.println(Arrays.toString(topKFrequentElements.getKFrequentElement(arr, k)));

        //18
//        int[] arr = new int[]{-1,0};
//        int target = -1;
//        TwoSumSortedArray twoSumSortedArray = new TwoSumSortedArray();
//        System.out.println(Arrays.toString(twoSumSortedArray.getTwoSumIndex(arr, target)));

        //19
//        int[] arr = new int[]{1,3,2,5,25,24,5};
//        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
//        System.out.println(containerWithMostWater.getMaxWater(arr));

        //20
//        String s1 = "ab";
//        String s2 = "eidbaooo";
//        PermutationInString permutationInString = new PermutationInString();
//        System.out.println(permutationInString.permutationExist(s1,s2));

        //21
//        String[] arr = new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        //"4","13","5","/","+"
        //"10","6","9","3","+","-11","*","/","*","17","+","5","+"

//        (10 * (6 / ((9 + 3) * -11))) + 17) + 5
//        EvaluateReversePolishAnnotation evaluateReversePolishAnnotation = new EvaluateReversePolishAnnotation();
//        System.out.println(evaluateReversePolishAnnotation.evaluate(arr));

        //22
//        int[] arr = new int[]{73,74,75,71,69,72,76,73};
//        DailyTemperature dailyTemperature = new DailyTemperature();
//        System.out.println(Arrays.toString(dailyTemperature.dailyTemperatures(arr)));

        //23
//        int[] positions = new int[]{10,8,0,5,3};
//        int[] speeds = new int[]{2,4,1,1,3};
//        int target = 12;
//        CarFleet carFleet = new CarFleet();
//        System.out.println(carFleet.getNumberOfFleet(target,positions,speeds));
//        //24
//        int[] arr = new int[] {-1,0,3,5,9,12};
//        BinarySearch binarySearch = new BinarySearch();
//        System.out.println(binarySearch.search(arr,-1));

        //25
//        int[][] arr = new int[][]{{1,3,5,7},{10,11,16,20}};
//        SearchIn2DMatrix searchIn2DMatrix = new SearchIn2DMatrix();
//        System.out.println(searchIn2DMatrix.search(arr,11));

        //26
//        int[] arr = new int[]{30,11,23,4,20};
//        KokoEatingBananas kokoEatingBananas = new KokoEatingBananas();
//        System.out.println(kokoEatingBananas.getBananaCount(arr,6));

        //27
//        String s1 = "abcde";
//        String s2 = "ace";
//        LongestCommonSubSequence longestCommonSubSequence = new LongestCommonSubSequence();
//        System.out.println(longestCommonSubSequence.getLongestSubSequenceLength(s1,s2));

        //28
//        String s = "bbbab";
//        LongestPalindromicSubSequence longestPalindromicSubSequence = new LongestPalindromicSubSequence();
//        System.out.println(longestPalindromicSubSequence.getLongest(s));

//        //29
//        int[] arr = new int[]{1, 4, 5, 3, 2};
//        SumOfAllSubArray sumOfAllSubArray = new SumOfAllSubArray();
//        System.out.println(sumOfAllSubArray.getSum(arr));

        //30
//        int[] arr = new int[]{1, 2, 0, 3};
//        EquilibriumIndexOfArray equilibriumIndexOfArray = new EquilibriumIndexOfArray();
//        System.out.println(equilibriumIndexOfArray.findEquilibriumIndex(arr));

        //31
//        int[] arr = new int[]{4,3,1,4,2};
//        FindTheDuplicateNumber findTheDuplicateNumber = new FindTheDuplicateNumber();
//        System.out.println(findTheDuplicateNumber.findDuplicateOptimal(arr));

        //32
//        int[] arr = new int[]{8,5,2,1,2};
//        CombinationSumII combinationSumII = new CombinationSumII();
//        System.out.println(combinationSumII.combinationSum2(arr,5));

        //33
//        int[] arr = new int[]{-9,2,3,-1,8,0};
//        MaximumProductSubArray maximumProductSubArray = new MaximumProductSubArray();
//        System.out.println(maximumProductSubArray.getMaxProduct(arr));

        //34
//        int[] arr = new int[]{-1};
//        RotateArray rotateArray = new RotateArray();
//        rotateArray.rotate(arr,2);
//        for(Integer i : arr) {
//            System.out.print(i+" ");
//        }

        //35
//        int[] arr  = new int[] {3,2,3};
//        MajorityElement majorityElement = new MajorityElement();
//        System.out.println(majorityElement.majorityElement(arr));

        //36
//        String s = "babad";
//        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
//        System.out.println(longestPalindromicSubstring.longestPalindromic(s));

        //37
//        String[] arr = new String[]{"eat","tea","tan","ate","nat","bat"};
//        GroupingOfAnagram groupingOfAnagram =  new GroupingOfAnagram();
//        System.out.println(groupingOfAnagram.groupAnagrams(arr));

        //38
//        int[] arr = new int[]{1,3,2,5,4,9,6,7,8,9};
//        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
//        System.out.println(longestConsecutiveSequence.longestConsecutive(arr));

        //39
//        int[] arr = new int[]{1,2,5};
//        CoinChangeProblem coinChangeProblem = new CoinChangeProblem();
//        System.out.println(coinChangeProblem.coinChange(arr,11));

//        Instant then = Instant.parse("2025-09-12T11:30:14.460Z");
//        long minutes = ChronoUnit.MINUTES.between(then, Instant.now());
//        System.out.println(minutes);

        //40
//        int[] arr = new int[] {1};
//        FindPeakElement findPeakElement = new FindPeakElement();
//        System.out.println(findPeakElement.getPeakElementOptimised(arr));

        //41

//        int[] arr = new int[]{1,2,3};
//        SubSets subSets = new SubSets();
//        System.out.println(subSets.subsets(arr));

        //42
//
//        int[] arr = new int[]{4,4,4,1,4};
//        SubSet2 subSet2 = new SubSet2();
//        System.out.println(subSet2.subsetsWithDup(arr));

        //43
        String s = "aab";
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        System.out.println(palindromePartitioning.partition(s));

    }
}
