package me.ruslanys.vkmusic.config

import javafx.fxml.FXMLLoader
import me.ruslanys.vkmusic.annotation.FxmlController
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import java.util.regex.Pattern

/**
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
/*
@Configuration
class FxmlConfiguration : ApplicationListener<ContextRefreshedEvent> {

    class RegistryPostProcessor : BeanDefinitionRegistryPostProcessor {

        @Throws(BeansException::class)
        override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        }

        // TODO: Try to set controller before loading a document
        @Throws(BeansException::class)
        override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
            val reflections = Reflections("me.ruslanys.vkmusic")
            val fxmlControllers = reflections.getTypesAnnotatedWith(FxmlController::class.java)
            val pattern = Pattern.compile("(\\w+)\\.fxml$")

            for (fxmlController in fxmlControllers) {
                val annotation = fxmlController.getAnnotation(FxmlController::class.java)
                val viewPath = annotation.view

                val matcher = pattern.matcher(viewPath)
                if (!matcher.find()) {
                    throw RuntimeException("Incorrect view path.")
                }
                val prefix = matcher.group(1)

                try {
                    javaClass.classLoader.getResourceAsStream(viewPath).use { fxmlStream ->
                        val loader = FXMLLoader()
                        loader.load<Any>(fxmlStream)

                        val viewName = prefix + "View"
                        val controllerName = prefix + "FxmlController"

                        val view = loader.getRoot<Any>()
                        val controller = loader.getController<Any>()

                        beanFactory.registerSingleton(viewName, view)
                        beanFactory.registerSingleton(controllerName, controller)
                    }
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }

            }

        }

    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val beans = event.applicationContext.getBeansWithAnnotation(FxmlController::class.java)
        val factory = event.applicationContext.autowireCapableBeanFactory

        for ((key, value) in beans) {
            factory.autowireBean(value)
            factory.initializeBean(value, key)
        }
    }

    companion object {
        @Bean
        @JvmStatic
        fun beanDefinitionRegistryPostProcessor(): BeanDefinitionRegistryPostProcessor {
            return RegistryPostProcessor()
        }
    }

}
*/
@Configuration
class Test : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val beans = event.applicationContext.getBeansWithAnnotation(FxmlController::class.java)
        val pattern = Pattern.compile("(\\w+)\\.fxml$")

        for ((_, value) in beans) {
            val annotation = value::class.java.getAnnotation(FxmlController::class.java)
            val viewPath = annotation.view

            val matcher = pattern.matcher(viewPath)
            if (!matcher.find()) {
                throw RuntimeException("Incorrect view path.")
            }

            javaClass.classLoader.getResourceAsStream(viewPath).use { fxmlStream ->
                val loader = FXMLLoader()
                loader.setController(value)
                loader.load<Any>(fxmlStream)

                val view = loader.getRoot<Any>()
                val controller = loader.getController<Any>()
            }
        }
    }

}