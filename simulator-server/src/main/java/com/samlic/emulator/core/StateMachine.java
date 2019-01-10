package com.samlic.emulator.core;

public interface StateMachine<S, T, R> {
	/**
	 * 输入数据
	 * @param input
	 * @return
	 */
	T next(S input);
	
	/**
	 * 返回数据
	 * @return
	 */
	R getValue();
	
	/**
	 * 获取回滚数据
	 * @return
	 */
	R getRollback();
}
