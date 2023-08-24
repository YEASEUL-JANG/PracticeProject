package recursive_tree_graph;

class Node{
	int data;
	Node lt,rt;//객체 Node타입의 변수를 만든다.
	public Node(int val) {
		data=val;
		lt=rt=null;
	}
}

public class Main {
	Node root;
	public void DFS(Node root) {
		if(root == null) return;
		else {
			//System.out.print(root.data+" ");//전위순회 출력
			DFS(root.lt);//재귀함수
			//System.out.print(root.data+" ");//중위순회 출력
			DFS(root.rt);//재귀함수
			System.out.print(root.data+" ");//후위순회 출력
		}
	}
	public static void main(String[] args) {
		Main tree = new Main();
		tree.root = new Node(1);// 데이터값이 1인 이진트리 생성
		tree.root.lt = new Node(2);//데이터값이 2인 이진트리를 생성하고 데이터1의 왼쪽 자식노드와 연결
		tree.root.rt = new Node(3);//데이터값이 3인 이진트리를 생성하고 데이터1의 오른쪽 자식노드와 연결
		tree.root.lt.lt = new Node(4);//데이터값이 4인 이진트리를 생성하고 데이터2의 왼쪽 자식노드와 연결
		tree.root.lt.rt = new Node(5);//데이터값이 5인 이진트리를 생성하고 데이터2의 오른쪽 자식노드와 연결
		tree.root.rt.lt = new Node(6);//데이터값이 6인 이진트리를 생성하고 데이터3의 왼쪽 자식노드와 연결
		tree.root.rt.rt = new Node(7);//데이터값이 7인 이진트리를 생성하고 데이터3의 오른쪽 자식노드와 연결
		tree.DFS(tree.root);
	}
}
