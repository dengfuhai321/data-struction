package problem_array_and_linklist;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 实现一种狗猫队列的结构,要求如下: 角户可以调用add方法将cat类或dog类的实例放入队列中;
 * 用户可以调用polIAlI方法,将队列中所有的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用polIDog方法,将队列中dog类的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用polICat方法,
 * 将队列中cat类的实例按照进队列的先后顺序依次弹出;用户可以调用isEmpty方法,
 * 检查队列中是否还有dog或cat的实例;用户可以调用isDogEmpty方法,
 * 检查队列中是否有dog类的实例;用户可以调用isCatEmpty方法,
 * 检查队列中是否有cat类的实例.
 * 
 * 实现思路：就是写一个包含猫队列和狗队列的大队列，然后出猫就猫队列出，出狗就狗队列出，如果按顺序出，可以在每次
 * 入队的时候给每一个宠物一个下标，来标记顺序。
 * 
 */
public class Code_06_DogCatQueue {
	public static class Pet {
		private String type;

		public Pet(String type) {
			this.type = type;
		}

		public String getPetType() {
			return this.type;
		}
	}

	public static class Dog extends Pet {

		public Dog() {
			super("dog");
		}

	}

	public static class Cat extends Pet {

		public Cat() {
			super("cat");
		}

	}

	public static class PetEnterQueue {
		private Pet pet;
		private long count;

		public PetEnterQueue(Pet pet, long count) {
			this.pet = pet;
			this.count = count;
		}

		public Pet getPet() {
			return this.pet;
		}

		public long getCount() {
			return this.count;
		}

		public String getPetType() {
			return this.getPet().getPetType();
		}

	}

	public static class CatDogQueue {
		private Queue<PetEnterQueue> dogQ;
		private Queue<PetEnterQueue> catQ;
		private long count;

		public CatDogQueue() {
			this.dogQ = new LinkedList<PetEnterQueue>();// 这就是队列，链表集合，适合进行增删操作
			this.catQ = new LinkedList<PetEnterQueue>();
			this.count = 0;
		}

		public void add(Pet pet) {
			// 属于什么类型就添加道那个队列中，count++
			if (pet.getPetType().equals("dog")) {
				this.dogQ.add(new PetEnterQueue(pet, this.count++));
			} else if (pet.getPetType().equals("cat")) {
				this.catQ.add(new PetEnterQueue(pet, this.count++));
			} else {
				throw new RuntimeException("err, not dog or cat");
			}

		}

		// 不管是猫还是狗按照顺序出队
		public Pet popAll() {
			// 如果两个队列都不为空
			if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
				// 分别把两个队列的队头取出查看谁的count小，就是看谁先入队了
				if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
					return this.dogQ.poll().getPet();
				} else {
					return this.catQ.poll().getPet();
				}
			} else if (!this.dogQ.isEmpty()) {
				return this.dogQ.poll().getPet();
			} else if (!this.catQ.isEmpty()) {
				return this.catQ.poll().getPet();
			} else {
				throw new RuntimeException("err, queue is empty!");
			}
		}

		public Cat popCat() {
			if (!this.isCatQueueEmpty()) {
				return (Cat) this.catQ.poll().getPet();
			} else
				throw new RuntimeException("Cat queue is empty!");
		}

		public Dog popDog() {
			if (!this.isDogQueueEmpty()) {
				return (Dog) this.dogQ.poll().getPet();
			} else {
				throw new RuntimeException("Dog queue is empty!");
			}

		}

		public boolean isEmpty() {
			return this.dogQ.isEmpty() && this.catQ.isEmpty();
		}

		public boolean isDogQueueEmpty() {
			return this.dogQ.isEmpty();
		}

		public boolean isCatQueueEmpty() {
			return this.catQ.isEmpty();
		}

	}

	public static void main(String[] args) {
		CatDogQueue test = new CatDogQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);
		while (!test.isDogQueueEmpty()) {
			System.out.println(test.popDog().getPetType());
		}
		while (!test.isEmpty()) {
			System.out.println(test.popAll().getPetType());
		}
	}

}
