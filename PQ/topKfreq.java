/* Approach 1: Using Maxheap - Node structure
Runtime: O(nlogn)
*/

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        if ( words == null || words.length == 0 || k <= 0){
            return ans;
        }
        
        // max queue
        PriorityQueue<Node> queue = new PriorityQueue<>(k, new Comparator<Node>() {
           public int compare(Node a, Node b){
               if (a.freq == b.freq){
                   return a.word.compareTo(b.word);
               } 
               
               return b.freq - a.freq;
           } 
        });
        
        HashMap<String, Node> map = new HashMap<>();
        for (String word: words){
            map.putIfAbsent(word, new Node(word,0));
            map.get(word).freq += 1;
        }
        
        for (Map.Entry<String, Node> entry : map.entrySet()){
            queue.offer(entry.getValue());
        }
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll().word);
        }

        return ans;
    }
    
    class Node {
        int freq;
        String word;
        
        public Node(String word, int freq){
            this.word = word;
            this.freq = freq;
        }
    }
}


/* Approach 2 - Without having to make Node structure */

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        Map<String, Integer> count = new HashMap();
        for (String word: words){
            // count.putIfAbsent(word, 0);
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        // maxHeap
        PriorityQueue<String> maxHeap = new PriorityQueue<String>(k, (a,b) -> count.get(a).equals(count.get(b)) ?
                a.compareTo(b) : count.get(b) - count.get(a)       
        );
        
        for (String word: count.keySet()){
            maxHeap.offer(word);
        }
        
        List<String> ans = new ArrayList<>();
        
        for (int i = 0; i<k ; i++){
            ans.add(maxHeap.poll());
        }
        
        return ans;
    }
    
}
