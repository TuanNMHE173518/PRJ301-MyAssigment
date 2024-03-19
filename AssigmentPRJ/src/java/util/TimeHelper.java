/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TimeHelper {
    public String getDayofWeek(java.sql.Date date){
        SimpleDateFormat dateformat = new SimpleDateFormat("EEEE");
        java.util.Date dateafter = new java.util.Date(date.getTime());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateafter);
        String day = dateformat.format(dateafter);
        return day;
        
    }
    
    public ArrayList<Integer> generateYears() {
        ArrayList<Integer> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 3; i--) {
            years.add(i);
        }
        return years;
    }

    public ArrayList<String> generateWeeks(int year) {
        ArrayList<String> weeks = new ArrayList<>();

        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        
        while (calendar.get(Calendar.YEAR) == year) {
            
            String weekStart = formatDate(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            String weekEnd = formatDate(calendar.getTime());

            
            String weekRange = "" + weekStart + " to " + weekEnd;
            weeks.add(weekRange);

            
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return weeks;
    }
    
    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days); 
        return calendar.getTime();
    }
    
    public String getCurrentWeek() {
        // Lấy một thể hiện của Calendar và thiết lập nó đến ngày hiện tại
        Calendar calendar = Calendar.getInstance();

        
        Date currentDate = calendar.getTime();

        // Đặt thứ hai của tuần là ngày bắt đầu
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // Lấy ngày bắt đầu và ngày kết thúc của tuần
        Date weekStartDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndDate = calendar.getTime();

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDateFormat = dateFormat.format(weekStartDate);
        String endDateFormat = dateFormat.format(weekEndDate);

        
        return startDateFormat + " to " + endDateFormat;
    }

    public String generateFirstWeek(int year) {
        // Tạo Calendar và thiết lập nó đến ngày đầu tiên của năm
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Di chuyển đến ngày đầu tiên của tuần
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Lấy ngày bắt đầu và kết thúc của tuần
        String weekStart = formatDate(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        String weekEnd = formatDate(calendar.getTime());

        
        return "" + weekStart + " to " + weekEnd;
    }
    public static ArrayList<java.sql.Date> toList(java.sql.Date from, java.sql.Date to)
    {
        ArrayList<java.sql.Date> list = new ArrayList<>();
        Date temp = from;
        while(!temp.after(to))
        {
            list.add(convertUtilToSql(temp));
            temp = addDaysToDate(temp, 1);
        }
        return list;
    }
     
    public static java.sql.Date convertUtilToSql(Date utilDate) {
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }

    public static String formatDate(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
