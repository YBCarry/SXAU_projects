package State;

//抽象状态类

public abstract class HeroState {
	Hero hero;//权限不是私有
	public abstract void check();//控制战机切换

}
