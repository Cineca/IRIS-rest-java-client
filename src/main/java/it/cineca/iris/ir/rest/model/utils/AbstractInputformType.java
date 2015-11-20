package it.cineca.iris.ir.rest.model.utils;

public abstract class AbstractInputformType implements IInputformType {

	@SuppressWarnings("unchecked")
	public static final <T extends AbstractInputformType> T getInstance (String type) {
		T typeEnum = null;

		try {
			typeEnum = (T) InputTypeEnum.valueOf(type).getClazz().newInstance();
		}

		catch (Exception e) {
			throw new UnsupportedOperationException();
		}

		return typeEnum;
	}
}
