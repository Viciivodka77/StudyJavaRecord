package StudyDay26;

public class EquationsPossible {

    public static void main(String[] args) {
        String[] e = new String[]{"c==c","b==d","x!=z"};
        EquationsPossible ep = new EquationsPossible();
        boolean b = ep.equationsPossible(e);
        System.out.println(b);
    }

    public boolean equationsPossible(String[] equations) {
        int len = equations.length;
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '='){
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                union(parent,index1,index2);
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!'){
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                if (find(parent,index1) == find(parent,index2)){
                    return false;
                }
            }
        }
        return true;
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent,index1)] = find(parent,index2);
    }

    private int find(int[] parent, int i) {
        while (parent[i] != i){
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}
