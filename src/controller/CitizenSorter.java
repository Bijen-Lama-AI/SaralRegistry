
package controller;

import model.CitizenModel;
import java.util.ArrayList;

/**
 * Provides sort by citizenship number and by age 
 * @author bijen
 */
public class CitizenSorter {
    
    /**
     * Sorts citizens by citizenship number in ascending order using insertion sort
     * @param citizens 
     */
    public static void sortByCitizenshipNumber(ArrayList<CitizenModel> citizens) {
        for (int i = 1; i < citizens.size(); i++) {
            CitizenModel key = citizens.get(i);
            String keyNumber = key.getCitizenshipNumber();
            int j = i -1;
            
            while (j >= 0 && citizens.get(j).getCitizenshipNumber().compareTo(keyNumber) > 0) {
                citizens.set(j + 1, citizens.get(j));
                j--;
            }
            citizens.set(j + 1, key);
        }
    }
    
    /**
     * Sorts citizens by age in ascending order using merge sort.
     * @param citizens 
     */
    public static void sortByAge(ArrayList<CitizenModel> citizens) {
        if (citizens == null || citizens.size() <= 1) {
            return;
        }
        mergeSort(citizens, 0, citizens.size() - 1);
    }
    
    /**
     * Recursive method to divide list into smaller parts
     */
    private static void mergeSort(ArrayList<CitizenModel> list, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        
        mergeSort(list, left, mid);
        
        mergeSort(list, mid + 1, right);
        
        merge(list, left, mid, right);
    }
    
    /**
     * Merge two sorted sub list into single
     */
    private static void merge(ArrayList<CitizenModel> list, int left, int mid, int right) {
        ArrayList<CitizenModel> temp = new ArrayList<>();
        
        int i = left;
        int j = mid + 1;
        
        while (i <= mid && j <= right) {
            if (list.get(i).getAge() <= list.get(j).getAge()) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }
        
        while (i <= mid) {
            temp.add(list.get(i));
            i++;
        }
        
        while (j <= right) {
            temp.add(list.get(j));
            j++;
        }
        
        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}
