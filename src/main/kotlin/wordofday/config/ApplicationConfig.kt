package wordofday.config

import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.stereotype.Component

@Component
class CustomizationBean : WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
    override fun customize(factory: ConfigurableServletWebServerFactory?) {
        factory?.let {
            it.setContextPath("/wordofday")
        }
    }
}