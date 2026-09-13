package access_modifiers.assignment_problems;
class LibraryMembers{
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;
    public LibraryMembers(){
        this(null,null);
    }
    public LibraryMembers(String name){
        this(null,name);
    }
    public LibraryMembers(String membershipId,String name){
        this.membershipId=membershipId;
        this.name=name;
    }
    String getMembershipId(){
        return membershipId;
    }
    void setMembershipId(String id){
        if(membershipId==null)
            membershipId=id;
    }
    boolean isPremiumMember(){
        return premiumMember;
    }
    void setPremiumMember(boolean premium){
        premiumMember=premium;
    }
    void setSecurityAnswer(String answer){
        securityAnswer=answer.hashCode()+"";
    }
    public static void main(String[] args){
        LibraryMembers m1=new LibraryMembers("Priya Nair");
        System.out.println(m1.getMembershipId());

        LibraryMembers m2=new LibraryMembers("LIB-8841","Priya Nair");
        System.out.println(m2.getMembershipId());

        LibraryMembers m3=new LibraryMembers();
        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");
        System.out.println(m3.getMembershipId());
    }
}