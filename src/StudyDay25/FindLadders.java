package StudyDay25;

import java.util.*;

public class FindLadders {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //先把wordList放入哈希表里，便于判断某个单词是否再wordList里
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        //特解判断
        if (wordSet.size() == 0 || !wordSet.contains(endWord)){
            return res;
        }

        //1:使用广度优先遍历得到后继节点列表successors
        //key：字符串，value：广度优先遍历过程中key的后继结点列表
        Map<String, Set<String>> successors = new HashMap<>();
        boolean found = bfs(beginWord,endWord,wordSet,successors);
        //如果已经找到则直接返回
        if (!found){
            return res;
        }

        //2:基于后继节点列表successors，使用回溯算法得到所有最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs(beginWord,endWord,successors,path,res);
        return res;
    }

    private void dfs(String beginWord, String endWord, Map<String, Set<String>> successors, Deque<String> path, List<List<String>> res) {
        //如果当前开始单词就是endWord，则直接返回当前path
        if (beginWord.equals(endWord)){
            res.add(new ArrayList<>(path));
            return;
        }
        //如果bfs访问结果里没有包含beginWord
        if (!successors.containsKey(beginWord)){
            return;
        }
        //回溯
        Set<String> successWords = successors.get(beginWord);
        for (String nextWord : successWords) {
            path.addLast(nextWord);
            dfs(nextWord,endWord,successors,path,res);
            path.removeLast();
        }
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, Set<String>> successors) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        //记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;//设置返回值
        int wordLen = beginWord.length();//单词长度一致

        //当层访问过的节点，当前层全部遍历完成后，再添加到总的visited集合中
        Set<String> nextLevelVisited = new HashSet<>();
        while(!queue.isEmpty()){
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                String currentWord = queue.poll();//拿道当前队列中的第一个单词
                char[] charArray = currentWord.toCharArray();
                for (int j = 0; j < wordLen; j++) {
                    //拿到原来单词的某个字符
                    char originChar = charArray[j];
                    //循环改变每个字符 得到可能变成的单词
                    for (char k = 'a';k <= 'z' ;k++){
                        //如果当前字符等于要变成的字符则跳过
                        if (charArray[j] == k){
                            continue;
                        }
                        charArray[j] = k;
                        //得到改变后的单词
                        String nextWord = new String(charArray);
                        //为了遵守转换过程中的中间单词必须是字典中的单词的规则
                        //如果单词列表中含有该改变后的单词
                        if (wordSet.contains(nextWord)){
                            //如果没有访问该单词
                            if (!visited.contains(nextWord)){
                                //如果当前改变的单词就是endWord那就设置返回值为true
                                if (nextWord.equals(endWord)){
                                    found = true;
                                }
                                //加入到下一级当中
                                nextLevelVisited.add(nextWord);
                                //入队
                                queue.offer(nextWord);
                                //维护successors的定义
                                successors.computeIfAbsent(currentWord, a ->new HashSet<>());//方法相当于通过get方法取值，并且做了空值判断
                                successors.get(currentWord).add(nextWord);
                            }
                        }
                    }
                    //将被改变的字符变回原始字符,使其他位的字符进行下一步判断
                    charArray[j] = originChar;
                }
            }
            //当前层level遍历结束
            //判断是否已经找到endWord
            if (found){
                break;
            }
            //将当前层已经全部访问完的节点 加入到visited中,并且清空
            visited.addAll(nextLevelVisited);
            nextLevelVisited.clear();
        }
        return found;
    }


}
