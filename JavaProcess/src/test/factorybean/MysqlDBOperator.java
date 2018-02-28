package test.factorybean;

public class MysqlDBOperator implements DBOperator<MysqlDBEntity>{

	@Override
	public int save(MysqlDBEntity t) {
		System.out.println("save MysqlDBEntity: this object " + t.getAttribute());
		return 0;
	}

	@Override
	public int update(MysqlDBEntity t) {
		System.out.println("update MysqlDBEntity: this object " + t.getAttribute());
		return 0;
	}

	@Override
	public int delete(MysqlDBEntity t) {
		System.out.println("delete MysqlDBEntity: this object " + t.getAttribute());
		return 0;
	}

	@Override
	public MysqlDBEntity select(Integer id) {
		System.out.println("select MysqlDBEntity: this id " + id);
		return null;
	}

}
