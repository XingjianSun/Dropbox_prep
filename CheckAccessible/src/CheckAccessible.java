import java.util.*;

public class CheckAccessible {

    private Map<String, String> foldersParent;
    private Set<String> access;

    public CheckAccessible(Map<String, String> foldersParent, Set<String> access){
        this.foldersParent = foldersParent;
        this.access = access;

    }

    public boolean hasAccess(String folderName){
        String curFolder = folderName;
        while(curFolder != null){
            if(access.contains (curFolder)){
                return true;
            }
            else{
                curFolder = foldersParent.get(curFolder);
            }
        }
        return false;

    }

    public void fixRedundantAccess(){
        Set<String> fixedAccess = new HashSet<>();
        for(String dir: access){
            String curDir = foldersParent.get(dir);
            boolean shouldRemove = false;
            while(curDir != null && !shouldRemove){
                if(access.contains (curDir)){
                    shouldRemove = true;
                }
                else{
                    curDir = foldersParent.get(curDir);
                }
            }
            if(!shouldRemove){
                fixedAccess.add(dir);
            }
        }
        this.access = fixedAccess;
    }


    public static void main(String[] args){
        Map<String, String> foldersParent = new HashMap<>();
        foldersParent.put("B", "A");
        foldersParent.put("C", "B");
        foldersParent.put("D", "B");
        foldersParent.put("E", "A");
        foldersParent.put("F", "E");

        Set<String> access = new HashSet<>();
        access.add("C");
        access.add("E");
        access.add("B");
        access.add("F");
        //access.add("A");

        CheckAccessible checker = new CheckAccessible(foldersParent, access);
        checker.fixRedundantAccess ();

        for(String s: checker.access){
            System.out.println (s);
        }

        Set<String> tests = new HashSet<> ();
        tests.add("B");
        tests.add("C");
        tests.add("F");
        tests.add("G");
        for(String test: tests){
            System.out.println (checker.hasAccess (test));
        }
    }
}
