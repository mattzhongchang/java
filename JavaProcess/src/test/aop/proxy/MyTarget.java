package test.aop.proxy;

public class MyTarget {

	private Target target;

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public void hello()
	{
		this.target.sayHello();
	}
}
