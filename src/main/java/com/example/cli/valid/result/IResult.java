package com.example.cli.valid.result;

import java.util.List;

public interface IResult {

    /**
     * 是否全部通过验证
     */
    boolean pass();

    /**
     * 未通过的列表信息
     * @return 验证结果
     */
    List<IConstraintResult> notPassList();

    /**
     * 所有的验证结果列表
     * @return 所有的验证结果
     */
    List<IConstraintResult> allList();

    /**
     * 输出信息到控台
     * （1）主要是为了方便调整
     * （2）该功能其实可以做增强，比如输出到文件/数据库等等。
     */
    IResult print();

    /**
     * 未通过的信息，
     *
     * @return this
     */
    IResult throwsEx();
}
