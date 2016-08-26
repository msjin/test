package com.msj.thread;

public class SynchronizedDemo implements Runnable {

	private boolean who = true;
	
	public static void main(String[] args) {
//		for(;;){
		for(int i=0;i<10;i++){
			SynchronizedDemo demo = new SynchronizedDemo();
			Thread t = new Thread(demo);
			t.start();
		}
	}
	
	@Override
	public void run() {
		if(who){
			product();
			who = false;
		}else{
			consume();
			who = true;
		}
	}
	
	private int product = 0;
	private int MAX_PRODUCT = 10;
	private int MIN_PRODUCT = 0;
	
	public synchronized void product(){
		if(this.product >= MAX_PRODUCT){
			try {
				wait();
				System.out.println("产品满了，稍后生成");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.product++;
		notifyAll();
	}
	
	public synchronized void consume(){
		if(product <= MIN_PRODUCT){
			try {
				wait();
				System.out.println("缺货，稍后取");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.product--;
		System.out.println("消费者取走了第"+this.product+"个产品");
		notifyAll();
	}

}
