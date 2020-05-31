class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int counter = 0;

        for (Integer i : list1) {
            if (i == elem) {
                counter++;
            }
        }
        for (Integer i : list2) {
            if (i == elem) {
                counter--;
            }
        }
        return counter == 0;
    }
}