package test.factorybean;

public class RedisDBOperator implements DBOperator<RedisDBEntity>{

	@Override
	public int save(RedisDBEntity t) {
		System.out.println("save RedisDBOperator: this object " + t.getJsonStr());
		return 0;
	}

	@Override
	public int update(RedisDBEntity t) {
		System.out.println("save RedisDBOperator: this object " + t.getJsonStr());
		return 0;
	}

	@Override
	public int delete(RedisDBEntity t) {
		System.out.println("save RedisDBOperator: this object " + t.getJsonStr());
		return 0;
	}

	@Override
	public RedisDBEntity select(Integer id) {
		System.out.println("save RedisDBOperator: this id " + id);
		return null;
	}

	
}
