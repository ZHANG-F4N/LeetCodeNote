import java.util.*;

public class a506findRelativeRanks {
    public static void main(String[] args) {
        System.out.println(findRelativeRanks(new int[]{5, 4, 3, 2, 1}));
        System.out.println(findRelativeRanks(new int[]{10, 3, 8, 9, 4}));
    }

    public static String[] findRelativeRanks(int[] score) {
        int len=score.length;
        String [] rank = new String[len];
        int max=0;
        for(int i=0;i<len;++i)
        {
            max=Math.max(max,score[i]);
        }
        int[] a=new int[max+1];
        for(int i=0;i<len;++i)
        {
            a[score[i]]=i+1;
        }
        int count=1;
        for(int i=max;i>=0;--i)
        {
            if(a[i]!=0)
            {
                switch(count)
                {
                    case 1:
                        rank[a[i]-1]="Gold Medal";
                        break;
                    case 2:
                        rank[a[i]-1]="Silver Medal";
                        break;
                    case 3:
                        rank[a[i]-1]="Bronze Medal";
                        break;
                    default:
                        rank[a[i]-1]=Integer.toString(count);
                        break;
                }
                count++;
            }
        }
        return rank;
    }
}
