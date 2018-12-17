package com.run.apidoc.api.service;

import com.run.apidoc.entity.Project.STAGE_TYPE;
import com.run.apidoc.utils.GenerateUtils.GeneratorType;
import com.run.apidoc.utils.GenerateUtils.TargetObjectType;

/**
 * generator interface, all generator class should implement this interface
 *
 * @author Bosn
 */
public interface Generator {

	/**
	 * is available on specific stage
	 *
	 * @param stage
	 * @return
	 */
	boolean isAvailable(STAGE_TYPE stage);



	/**
	 * get generator type
	 *
	 * @return
	 */
	GeneratorType getGeneratorType();



	/**
	 * get author
	 *
	 * @return author
	 */
	String getAuthor();



	/**
	 * get introduction of generator
	 *
	 * @return
	 */
	String getIntroduction();



	/**
	 * get target object type
	 *
	 * @return
	 */
	TargetObjectType getTargetObjectType();



	/**
	 * this method will be invoked automatically by RAP before using, the type
	 * of Object is decided by the return value of getTargetObjectType() method,
	 * eg. if result PROJECT, RAP will pass the proper Project object
	 * automatically.
	 *
	 * @param project
	 */
	void setObject(Object obj);



	/**
	 * do generate
	 *
	 * @return generated result
	 */
	String doGenerate();
}
