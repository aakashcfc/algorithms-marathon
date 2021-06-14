package Graphs;

import java.util.HashMap;
import java.util.Map;

class FamilyForest {

    Map<String , Member> map= new HashMap<>();
    public class Member {

        String name;
        Member parent;
        int rank;

    }

    public void make_family(String s) {

        Member member = new Member();
        member.name = s;
        member.parent = member;
        member.rank = 0;
        map.put(s , member);

    }

    public String union (String s, String t){

        Member m1 = map.get(s);
        Member m2 = map.get(t);
        Member p1 = findSet(m1);
        Member p2 = findSet(m2);

        if(p1.name == p2.name)
            return p1.name;
        if (p1.rank >= p2.rank) {
            if(p1.rank == p2.rank)
                p1.rank = p1.rank + 1;

            p2.parent=p1;
            return p1.name;
        }
        else {
            p1.parent = p2;
            return p2.name;
        }

    }

    public String find (String s){

        return findSet(map.get(s)).name;
    }


    private Member findSet(Member member) {

        Member parent = member.parent;
        if (parent == member) {
            return parent;
        }
        member.parent = findSet(member.parent);
        return member.parent;

    }

}