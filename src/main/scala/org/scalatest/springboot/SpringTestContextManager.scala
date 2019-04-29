package org.scalatest.springboot

/**
  * author prayagupd
  * on 1/29/17.
  */

import org.scalatest.{BeforeAndAfterAll, Suite}
import org.springframework.core.annotation.{AnnotatedElementUtils, AnnotationAttributes}
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.{TestContext, TestContextManager}
import org.springframework.util.Assert

trait SpringTestContextManager extends BeforeAndAfterAll { this: Suite =>

  private val springTestContextManager: TestContextManager = new TestContextManager(this.getClass)

  abstract override protected def beforeAll(): Unit = {
    super.beforeAll
    springTestContextManager.registerTestExecutionListeners(ForeverDirtiesContextTEL)
    springTestContextManager.beforeTestClass()
    springTestContextManager.prepareTestInstance(this)
  }

  abstract override protected def afterAll(): Unit = {
    springTestContextManager.afterTestClass()
    super.afterAll
  }
}

protected object ForeverDirtiesContextTEL extends DirtiesContextTestExecutionListener {

  @throws(classOf[Exception])
  override def afterTestClass(springTestContext: TestContext) {
    val springTestClass: Class[_] = springTestContext.getTestClass
    Assert.notNull(springTestClass, "spring test class of the supplied TestContext can not be null")

    val dirtiesContextAnnotationsAttributes: AnnotationAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(
      springTestClass, classOf[DirtiesContext].getName)

    val dirtiesContextHierarchyMode: DirtiesContext.HierarchyMode =
      if (dirtiesContextAnnotationsAttributes == null) null
      else dirtiesContextAnnotationsAttributes.getEnum[DirtiesContext.HierarchyMode]("hierarchyMode")
    dirtyContext(springTestContext, dirtiesContextHierarchyMode)
  }

}
