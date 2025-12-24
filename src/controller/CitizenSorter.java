/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.CitizenModel;
import java.util.ArrayList;

/**
 *  
 * @author bijen
 */
public class CitizenSorter {
    
    /**
     * Sorts citizens by citizenship number using insertion sort
     * @param citizens 
     */
    public static void sortByCitizenshipNumber(ArrayList<CitizenModel> citizens) {
        for (int i = 1; i < citizens.size(); i++) {
            CitizenModel key = citizens.get(i);
            int j = i -1;
            
            while (j >= 0 && citizens.get(j).getCitizenshipNumber().compareTo(key.getCitizenshipNumber()) > 0) {
                citizens.set(j + 1, key);
            }
        }
    }
    
    // Merge sort of age
    public static void sortByAge(ArrayList<CitizenModel> citizens) {
        if (citizens == null || citizens.size() <= 1) {
            return;
        }
        mergeSort(citizens, 0, citizens.size() - 1);
    }
    
    private static void mergeSort(ArrayList<CitizenModel> list, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = (left + right) / 2;
        
        mergeSort(list, left, mid);
        mergeSort(list, mid + 1, right);
        
        merge(list, left, mid, right);
    }
    
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
