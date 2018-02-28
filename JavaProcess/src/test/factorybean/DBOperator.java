package test.factorybean;

public interface DBOperator<T extends DBEntity>{
	
	int save(T t);
	
	int update(T t);
	
	int delete(T t);
	
	T select(Integer id);

}
