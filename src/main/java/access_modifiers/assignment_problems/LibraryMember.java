package access_modifiers.assignment_problems;
class LibraryMembershipChecker{
    static String classifyAccess(String fieldModifier,String accessorContext){
        if(fieldModifier.equals("private")){
            if(accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }
        if(fieldModifier.equals("default")){
            if(accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }
        if(fieldModifier.equals("protected")){
            if(accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }
        if(fieldModifier.equals("public"))
            return "ALLOWED";
        return "DENIED";
    }
    static String summarizeByModifier(String[][] attempts){
        int privateAllowed=0,privateDenied=0,defaultAllowed=0,defaultDenied=0,protectedAllowed=0,protectedDenied=0,publicAllowed=0,publicDenied=0;
        for(String[] attempt:attempts){
            if(attempt[0].equals("private")){
                if(classifyAccess(attempt[0],attempt[1]).equals("ALLOWED"))
                    privateAllowed++;
                else
                    privateDenied++;
            }
            if(attempt[0].equals("default")){
                if(classifyAccess(attempt[0],attempt[1]).equals("ALLOWED"))
                    defaultAllowed++;
                else
                    defaultDenied++;
            }
            if(attempt[0].equals("protected")){
                if(classifyAccess(attempt[0],attempt[1]).equals("ALLOWED"))
                    protectedAllowed++;
                else
                    protectedDenied++;
            }
            if(attempt[0].equals("public")){
                if(classifyAccess(attempt[0],attempt[1]).equals("ALLOWED"))
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }
        return "private: "+privateAllowed+" allowed / "+privateDenied+
                " denied | default: "+defaultAllowed+" allowed / "+defaultDenied+
                " denied | protected: "+protectedAllowed+" allowed / "+protectedDenied+
                " denied | public: "+publicAllowed+" allowed / "+publicDenied+" denied";
    }
    public static void main(String[] args){
        String[][] attempts={
                {"private","SAME_CLASS"},
                {"private","SAME_PACKAGE"},
                {"default","SAME_PACKAGE"},
                {"default","DIFFERENT_PACKAGE"},
                {"protected","SAME_PACKAGE"},
                {"protected","SAME_CLASS"},
                {"public","DIFFERENT_PACKAGE"}
        };

        System.out.println(classifyAccess("private","SAME_CLASS"));
        System.out.println(classifyAccess("protected","DIFFERENT_PACKAGE"));
        System.out.println(summarizeByModifier(attempts));
        try{
            LibraryMember m1=new LibraryMember("LB9","BR1",0,"Priya Nair");
        }catch(IllegalArgumentException e){
            System.out.println("construction rejected");
        }
        LibraryMember m2=new LibraryMember("LB94","BR1",0,"Priya Nair");
    }
}
class LibraryMember{
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;
    public LibraryMember(String membershipId,String branchCode,double finesOwed,String displayName){
        String trimmed=membershipId.trim();
        if(trimmed.isEmpty()||trimmed.length()<4)
            throw new IllegalArgumentException("Invalid ID");
        this.membershipId=trimmed;
        this.branchCode=branchCode;
        this.finesOwed=finesOwed;
        this.displayName=displayName;
    }
}