package sort;

/*
 * 比较器
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code_06_Comparator {
	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	// id从小到大排序
	public static class IdAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.id - o2.id;// o1>o2,返回正数，第二个数o2应该放前面,o1<o2,返回负数，第一个数o1就应该放前面,0就是等于
		}

	}

	// id从大到小排序
	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;// 比较器中返回负数就是o1>02,返回正数就是o1<o2,返回0就是o1=o2
		}

	}

	public static class AgeAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}

	}

	public static class AgeDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;
		}

	}

	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
		System.out.println("===========================");
	}

	public static void main(String[] args) {
		Student student1 = new Student("A", 1, 23);
		Student student2 = new Student("B", 2, 21);
		Student student3 = new Student("C", 3, 22);

		Student[] students = new Student[] { student3, student2, student1 };
		printStudents(students);

		Arrays.sort(students, new IdAscendingComparator());// 使用java的排序，需要实现Comparator，重写比较的方法
		printStudents(students);

		Arrays.sort(students, new IdDescendingComparator());
		printStudents(students);

		Arrays.sort(students, new AgeAscendingComparator());
		printStudents(students);

		Arrays.sort(students, new AgeDescendingComparator());
		printStudents(students);
		// 使用堆排序
		PriorityQueue<Student> heap = new PriorityQueue<>(new IdAscendingComparator());// 添加比较器，告诉堆怎么排序
		heap.add(student1);
		heap.add(student2);
		heap.add(student3);
		while (!heap.isEmpty()) {
			Student student = heap.poll();// 这里推出二叉树（堆排序就是把数组想象成二叉树）的根节点
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
		System.out.println("===========================");
	}

}
