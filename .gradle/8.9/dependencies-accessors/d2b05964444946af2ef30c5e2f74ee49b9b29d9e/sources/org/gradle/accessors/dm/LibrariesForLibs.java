package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ChLibraryAccessors laccForChLibraryAccessors = new ChLibraryAccessors(owner);
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final JakartaLibraryAccessors laccForJakartaLibraryAccessors = new JakartaLibraryAccessors(owner);
    private final MysqlLibraryAccessors laccForMysqlLibraryAccessors = new MysqlLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>ch</b>
     */
    public ChLibraryAccessors getCh() {
        return laccForChLibraryAccessors;
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>jakarta</b>
     */
    public JakartaLibraryAccessors getJakarta() {
        return laccForJakartaLibraryAccessors;
    }

    /**
     * Group of libraries at <b>mysql</b>
     */
    public MysqlLibraryAccessors getMysql() {
        return laccForMysqlLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ChLibraryAccessors extends SubDependencyFactory {
        private final ChQosLibraryAccessors laccForChQosLibraryAccessors = new ChQosLibraryAccessors(owner);

        public ChLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>ch.qos</b>
         */
        public ChQosLibraryAccessors getQos() {
            return laccForChQosLibraryAccessors;
        }

    }

    public static class ChQosLibraryAccessors extends SubDependencyFactory {
        private final ChQosLogbackLibraryAccessors laccForChQosLogbackLibraryAccessors = new ChQosLogbackLibraryAccessors(owner);

        public ChQosLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>ch.qos.logback</b>
         */
        public ChQosLogbackLibraryAccessors getLogback() {
            return laccForChQosLogbackLibraryAccessors;
        }

    }

    public static class ChQosLogbackLibraryAccessors extends SubDependencyFactory {
        private final ChQosLogbackLogbackLibraryAccessors laccForChQosLogbackLogbackLibraryAccessors = new ChQosLogbackLogbackLibraryAccessors(owner);

        public ChQosLogbackLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>ch.qos.logback.logback</b>
         */
        public ChQosLogbackLogbackLibraryAccessors getLogback() {
            return laccForChQosLogbackLogbackLibraryAccessors;
        }

    }

    public static class ChQosLogbackLogbackLibraryAccessors extends SubDependencyFactory {

        public ChQosLogbackLogbackLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>classic</b> with <b>ch.qos.logback:logback-classic</b> coordinates and
         * with version reference <b>ch.qos.logback.logback.classic</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getClassic() {
            return create("ch.qos.logback.logback.classic");
        }

    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGooglecodeLibraryAccessors laccForComGooglecodeLibraryAccessors = new ComGooglecodeLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.googlecode</b>
         */
        public ComGooglecodeLibraryAccessors getGooglecode() {
            return laccForComGooglecodeLibraryAccessors;
        }

    }

    public static class ComGooglecodeLibraryAccessors extends SubDependencyFactory {
        private final ComGooglecodeJsonLibraryAccessors laccForComGooglecodeJsonLibraryAccessors = new ComGooglecodeJsonLibraryAccessors(owner);

        public ComGooglecodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.googlecode.json</b>
         */
        public ComGooglecodeJsonLibraryAccessors getJson() {
            return laccForComGooglecodeJsonLibraryAccessors;
        }

    }

    public static class ComGooglecodeJsonLibraryAccessors extends SubDependencyFactory {
        private final ComGooglecodeJsonSimpleLibraryAccessors laccForComGooglecodeJsonSimpleLibraryAccessors = new ComGooglecodeJsonSimpleLibraryAccessors(owner);

        public ComGooglecodeJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.googlecode.json.simple</b>
         */
        public ComGooglecodeJsonSimpleLibraryAccessors getSimple() {
            return laccForComGooglecodeJsonSimpleLibraryAccessors;
        }

    }

    public static class ComGooglecodeJsonSimpleLibraryAccessors extends SubDependencyFactory {
        private final ComGooglecodeJsonSimpleJsonLibraryAccessors laccForComGooglecodeJsonSimpleJsonLibraryAccessors = new ComGooglecodeJsonSimpleJsonLibraryAccessors(owner);

        public ComGooglecodeJsonSimpleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.googlecode.json.simple.json</b>
         */
        public ComGooglecodeJsonSimpleJsonLibraryAccessors getJson() {
            return laccForComGooglecodeJsonSimpleJsonLibraryAccessors;
        }

    }

    public static class ComGooglecodeJsonSimpleJsonLibraryAccessors extends SubDependencyFactory {

        public ComGooglecodeJsonSimpleJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>simple</b> with <b>com.googlecode.json-simple:json-simple</b> coordinates and
         * with version reference <b>com.googlecode.json.simple.json.simple</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSimple() {
            return create("com.googlecode.json.simple.json.simple");
        }

    }

    public static class JakartaLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletLibraryAccessors laccForJakartaServletLibraryAccessors = new JakartaServletLibraryAccessors(owner);

        public JakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet</b>
         */
        public JakartaServletLibraryAccessors getServlet() {
            return laccForJakartaServletLibraryAccessors;
        }

    }

    public static class JakartaServletLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletJspLibraryAccessors laccForJakartaServletJspLibraryAccessors = new JakartaServletJspLibraryAccessors(owner);

        public JakartaServletLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet.jsp</b>
         */
        public JakartaServletJspLibraryAccessors getJsp() {
            return laccForJakartaServletJspLibraryAccessors;
        }

    }

    public static class JakartaServletJspLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletJspJstlLibraryAccessors laccForJakartaServletJspJstlLibraryAccessors = new JakartaServletJspJstlLibraryAccessors(owner);

        public JakartaServletJspLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet.jsp.jstl</b>
         */
        public JakartaServletJspJstlLibraryAccessors getJstl() {
            return laccForJakartaServletJspJstlLibraryAccessors;
        }

    }

    public static class JakartaServletJspJstlLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletJspJstlJakartaLibraryAccessors laccForJakartaServletJspJstlJakartaLibraryAccessors = new JakartaServletJspJstlJakartaLibraryAccessors(owner);

        public JakartaServletJspJstlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet.jsp.jstl.jakarta</b>
         */
        public JakartaServletJspJstlJakartaLibraryAccessors getJakarta() {
            return laccForJakartaServletJspJstlJakartaLibraryAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletJspJstlJakartaServletLibraryAccessors laccForJakartaServletJspJstlJakartaServletLibraryAccessors = new JakartaServletJspJstlJakartaServletLibraryAccessors(owner);

        public JakartaServletJspJstlJakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet.jsp.jstl.jakarta.servlet</b>
         */
        public JakartaServletJspJstlJakartaServletLibraryAccessors getServlet() {
            return laccForJakartaServletJspJstlJakartaServletLibraryAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaServletLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletJspJstlJakartaServletJspLibraryAccessors laccForJakartaServletJspJstlJakartaServletJspLibraryAccessors = new JakartaServletJspJstlJakartaServletJspLibraryAccessors(owner);

        public JakartaServletJspJstlJakartaServletLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet.jsp.jstl.jakarta.servlet.jsp</b>
         */
        public JakartaServletJspJstlJakartaServletJspLibraryAccessors getJsp() {
            return laccForJakartaServletJspJstlJakartaServletJspLibraryAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaServletJspLibraryAccessors extends SubDependencyFactory {
        private final JakartaServletJspJstlJakartaServletJspJstlLibraryAccessors laccForJakartaServletJspJstlJakartaServletJspJstlLibraryAccessors = new JakartaServletJspJstlJakartaServletJspJstlLibraryAccessors(owner);

        public JakartaServletJspJstlJakartaServletJspLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl</b>
         */
        public JakartaServletJspJstlJakartaServletJspJstlLibraryAccessors getJstl() {
            return laccForJakartaServletJspJstlJakartaServletJspJstlLibraryAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaServletJspJstlLibraryAccessors extends SubDependencyFactory {

        public JakartaServletJspJstlJakartaServletJspJstlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api</b> coordinates and
         * with version reference <b>jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl.api");
        }

    }

    public static class MysqlLibraryAccessors extends SubDependencyFactory {
        private final MysqlMysqlLibraryAccessors laccForMysqlMysqlLibraryAccessors = new MysqlMysqlLibraryAccessors(owner);

        public MysqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>mysql.mysql</b>
         */
        public MysqlMysqlLibraryAccessors getMysql() {
            return laccForMysqlMysqlLibraryAccessors;
        }

    }

    public static class MysqlMysqlLibraryAccessors extends SubDependencyFactory {
        private final MysqlMysqlConnectorLibraryAccessors laccForMysqlMysqlConnectorLibraryAccessors = new MysqlMysqlConnectorLibraryAccessors(owner);

        public MysqlMysqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>mysql.mysql.connector</b>
         */
        public MysqlMysqlConnectorLibraryAccessors getConnector() {
            return laccForMysqlMysqlConnectorLibraryAccessors;
        }

    }

    public static class MysqlMysqlConnectorLibraryAccessors extends SubDependencyFactory {

        public MysqlMysqlConnectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>java</b> with <b>mysql:mysql-connector-java</b> coordinates and
         * with version reference <b>mysql.mysql.connector.java</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJava() {
            return create("mysql.mysql.connector.java");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLibraryAccessors laccForOrgApacheLibraryAccessors = new OrgApacheLibraryAccessors(owner);
        private final OrgBgeeLibraryAccessors laccForOrgBgeeLibraryAccessors = new OrgBgeeLibraryAccessors(owner);
        private final OrgGlassfishLibraryAccessors laccForOrgGlassfishLibraryAccessors = new OrgGlassfishLibraryAccessors(owner);
        private final OrgMybatisLibraryAccessors laccForOrgMybatisLibraryAccessors = new OrgMybatisLibraryAccessors(owner);
        private final OrgProjectlombokLibraryAccessors laccForOrgProjectlombokLibraryAccessors = new OrgProjectlombokLibraryAccessors(owner);
        private final OrgSpringdocLibraryAccessors laccForOrgSpringdocLibraryAccessors = new OrgSpringdocLibraryAccessors(owner);
        private final OrgSpringframeworkLibraryAccessors laccForOrgSpringframeworkLibraryAccessors = new OrgSpringframeworkLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache</b>
         */
        public OrgApacheLibraryAccessors getApache() {
            return laccForOrgApacheLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.bgee</b>
         */
        public OrgBgeeLibraryAccessors getBgee() {
            return laccForOrgBgeeLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.glassfish</b>
         */
        public OrgGlassfishLibraryAccessors getGlassfish() {
            return laccForOrgGlassfishLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.mybatis</b>
         */
        public OrgMybatisLibraryAccessors getMybatis() {
            return laccForOrgMybatisLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.projectlombok</b>
         */
        public OrgProjectlombokLibraryAccessors getProjectlombok() {
            return laccForOrgProjectlombokLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.springdoc</b>
         */
        public OrgSpringdocLibraryAccessors getSpringdoc() {
            return laccForOrgSpringdocLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.springframework</b>
         */
        public OrgSpringframeworkLibraryAccessors getSpringframework() {
            return laccForOrgSpringframeworkLibraryAccessors;
        }

    }

    public static class OrgApacheLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLoggingLibraryAccessors laccForOrgApacheLoggingLibraryAccessors = new OrgApacheLoggingLibraryAccessors(owner);
        private final OrgApachePoiLibraryAccessors laccForOrgApachePoiLibraryAccessors = new OrgApachePoiLibraryAccessors(owner);
        private final OrgApacheTomcatLibraryAccessors laccForOrgApacheTomcatLibraryAccessors = new OrgApacheTomcatLibraryAccessors(owner);

        public OrgApacheLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.logging</b>
         */
        public OrgApacheLoggingLibraryAccessors getLogging() {
            return laccForOrgApacheLoggingLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.apache.poi</b>
         */
        public OrgApachePoiLibraryAccessors getPoi() {
            return laccForOrgApachePoiLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.apache.tomcat</b>
         */
        public OrgApacheTomcatLibraryAccessors getTomcat() {
            return laccForOrgApacheTomcatLibraryAccessors;
        }

    }

    public static class OrgApacheLoggingLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLoggingLog4jLibraryAccessors laccForOrgApacheLoggingLog4jLibraryAccessors = new OrgApacheLoggingLog4jLibraryAccessors(owner);

        public OrgApacheLoggingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.logging.log4j</b>
         */
        public OrgApacheLoggingLog4jLibraryAccessors getLog4j() {
            return laccForOrgApacheLoggingLog4jLibraryAccessors;
        }

    }

    public static class OrgApacheLoggingLog4jLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLoggingLog4jLog4jLibraryAccessors laccForOrgApacheLoggingLog4jLog4jLibraryAccessors = new OrgApacheLoggingLog4jLog4jLibraryAccessors(owner);

        public OrgApacheLoggingLog4jLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.logging.log4j.log4j</b>
         */
        public OrgApacheLoggingLog4jLog4jLibraryAccessors getLog4j() {
            return laccForOrgApacheLoggingLog4jLog4jLibraryAccessors;
        }

    }

    public static class OrgApacheLoggingLog4jLog4jLibraryAccessors extends SubDependencyFactory {

        public OrgApacheLoggingLog4jLog4jLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.apache.logging.log4j:log4j-core</b> coordinates and
         * with version reference <b>org.apache.logging.log4j.log4j.core</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("org.apache.logging.log4j.log4j.core");
        }

    }

    public static class OrgApachePoiLibraryAccessors extends SubDependencyFactory {
        private final OrgApachePoiPoiLibraryAccessors laccForOrgApachePoiPoiLibraryAccessors = new OrgApachePoiPoiLibraryAccessors(owner);

        public OrgApachePoiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.poi.poi</b>
         */
        public OrgApachePoiPoiLibraryAccessors getPoi() {
            return laccForOrgApachePoiPoiLibraryAccessors;
        }

    }

    public static class OrgApachePoiPoiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public OrgApachePoiPoiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>poi</b> with <b>org.apache.poi:poi</b> coordinates and
         * with version reference <b>org.apache.poi.poi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("org.apache.poi.poi");
        }

        /**
         * Dependency provider for <b>ooxml</b> with <b>org.apache.poi:poi-ooxml</b> coordinates and
         * with version reference <b>org.apache.poi.poi.ooxml</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getOoxml() {
            return create("org.apache.poi.poi.ooxml");
        }

    }

    public static class OrgApacheTomcatLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheTomcatEmbedLibraryAccessors laccForOrgApacheTomcatEmbedLibraryAccessors = new OrgApacheTomcatEmbedLibraryAccessors(owner);

        public OrgApacheTomcatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.tomcat.embed</b>
         */
        public OrgApacheTomcatEmbedLibraryAccessors getEmbed() {
            return laccForOrgApacheTomcatEmbedLibraryAccessors;
        }

    }

    public static class OrgApacheTomcatEmbedLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheTomcatEmbedTomcatLibraryAccessors laccForOrgApacheTomcatEmbedTomcatLibraryAccessors = new OrgApacheTomcatEmbedTomcatLibraryAccessors(owner);

        public OrgApacheTomcatEmbedLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.tomcat.embed.tomcat</b>
         */
        public OrgApacheTomcatEmbedTomcatLibraryAccessors getTomcat() {
            return laccForOrgApacheTomcatEmbedTomcatLibraryAccessors;
        }

    }

    public static class OrgApacheTomcatEmbedTomcatLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheTomcatEmbedTomcatEmbedLibraryAccessors laccForOrgApacheTomcatEmbedTomcatEmbedLibraryAccessors = new OrgApacheTomcatEmbedTomcatEmbedLibraryAccessors(owner);

        public OrgApacheTomcatEmbedTomcatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.tomcat.embed.tomcat.embed</b>
         */
        public OrgApacheTomcatEmbedTomcatEmbedLibraryAccessors getEmbed() {
            return laccForOrgApacheTomcatEmbedTomcatEmbedLibraryAccessors;
        }

    }

    public static class OrgApacheTomcatEmbedTomcatEmbedLibraryAccessors extends SubDependencyFactory {

        public OrgApacheTomcatEmbedTomcatEmbedLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>jasper</b> with <b>org.apache.tomcat.embed:tomcat-embed-jasper</b> coordinates and
         * with version reference <b>org.apache.tomcat.embed.tomcat.embed.jasper</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJasper() {
            return create("org.apache.tomcat.embed.tomcat.embed.jasper");
        }

    }

    public static class OrgBgeeLibraryAccessors extends SubDependencyFactory {
        private final OrgBgeeLog4jdbcLibraryAccessors laccForOrgBgeeLog4jdbcLibraryAccessors = new OrgBgeeLog4jdbcLibraryAccessors(owner);

        public OrgBgeeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bgee.log4jdbc</b>
         */
        public OrgBgeeLog4jdbcLibraryAccessors getLog4jdbc() {
            return laccForOrgBgeeLog4jdbcLibraryAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLibraryAccessors extends SubDependencyFactory {
        private final OrgBgeeLog4jdbcLog4j2LibraryAccessors laccForOrgBgeeLog4jdbcLog4j2LibraryAccessors = new OrgBgeeLog4jdbcLog4j2LibraryAccessors(owner);

        public OrgBgeeLog4jdbcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bgee.log4jdbc.log4j2</b>
         */
        public OrgBgeeLog4jdbcLog4j2LibraryAccessors getLog4j2() {
            return laccForOrgBgeeLog4jdbcLog4j2LibraryAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2LibraryAccessors extends SubDependencyFactory {
        private final OrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors laccForOrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors = new OrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors(owner);

        public OrgBgeeLog4jdbcLog4j2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bgee.log4jdbc.log4j2.log4jdbc</b>
         */
        public OrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors getLog4jdbc() {
            return laccForOrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors extends SubDependencyFactory {
        private final OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors laccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors = new OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors(owner);

        public OrgBgeeLog4jdbcLog4j2Log4jdbcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bgee.log4jdbc.log4j2.log4jdbc.log4j2</b>
         */
        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors getLog4j2() {
            return laccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors extends SubDependencyFactory {
        private final OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors laccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors = new OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors(owner);

        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bgee.log4jdbc.log4j2.log4jdbc.log4j2.jdbc4</b>
         */
        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors getJdbc4() {
            return laccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors extends SubDependencyFactory {

        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>v1</b> with <b>org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1</b> coordinates and
         * with version reference <b>org.bgee.log4jdbc.log4j2.log4jdbc.log4j2.jdbc4.v1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getV1() {
            return create("org.bgee.log4jdbc.log4j2.log4jdbc.log4j2.jdbc4.v1");
        }

    }

    public static class OrgGlassfishLibraryAccessors extends SubDependencyFactory {
        private final OrgGlassfishWebLibraryAccessors laccForOrgGlassfishWebLibraryAccessors = new OrgGlassfishWebLibraryAccessors(owner);

        public OrgGlassfishLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.glassfish.web</b>
         */
        public OrgGlassfishWebLibraryAccessors getWeb() {
            return laccForOrgGlassfishWebLibraryAccessors;
        }

    }

    public static class OrgGlassfishWebLibraryAccessors extends SubDependencyFactory {
        private final OrgGlassfishWebJakartaLibraryAccessors laccForOrgGlassfishWebJakartaLibraryAccessors = new OrgGlassfishWebJakartaLibraryAccessors(owner);

        public OrgGlassfishWebLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.glassfish.web.jakarta</b>
         */
        public OrgGlassfishWebJakartaLibraryAccessors getJakarta() {
            return laccForOrgGlassfishWebJakartaLibraryAccessors;
        }

    }

    public static class OrgGlassfishWebJakartaLibraryAccessors extends SubDependencyFactory {
        private final OrgGlassfishWebJakartaServletLibraryAccessors laccForOrgGlassfishWebJakartaServletLibraryAccessors = new OrgGlassfishWebJakartaServletLibraryAccessors(owner);

        public OrgGlassfishWebJakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.glassfish.web.jakarta.servlet</b>
         */
        public OrgGlassfishWebJakartaServletLibraryAccessors getServlet() {
            return laccForOrgGlassfishWebJakartaServletLibraryAccessors;
        }

    }

    public static class OrgGlassfishWebJakartaServletLibraryAccessors extends SubDependencyFactory {
        private final OrgGlassfishWebJakartaServletJspLibraryAccessors laccForOrgGlassfishWebJakartaServletJspLibraryAccessors = new OrgGlassfishWebJakartaServletJspLibraryAccessors(owner);

        public OrgGlassfishWebJakartaServletLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.glassfish.web.jakarta.servlet.jsp</b>
         */
        public OrgGlassfishWebJakartaServletJspLibraryAccessors getJsp() {
            return laccForOrgGlassfishWebJakartaServletJspLibraryAccessors;
        }

    }

    public static class OrgGlassfishWebJakartaServletJspLibraryAccessors extends SubDependencyFactory {

        public OrgGlassfishWebJakartaServletJspLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>jstl</b> with <b>org.glassfish.web:jakarta.servlet.jsp.jstl</b> coordinates and
         * with version reference <b>org.glassfish.web.jakarta.servlet.jsp.jstl</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJstl() {
            return create("org.glassfish.web.jakarta.servlet.jsp.jstl");
        }

    }

    public static class OrgMybatisLibraryAccessors extends SubDependencyFactory {
        private final OrgMybatisSpringLibraryAccessors laccForOrgMybatisSpringLibraryAccessors = new OrgMybatisSpringLibraryAccessors(owner);

        public OrgMybatisLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.mybatis.spring</b>
         */
        public OrgMybatisSpringLibraryAccessors getSpring() {
            return laccForOrgMybatisSpringLibraryAccessors;
        }

    }

    public static class OrgMybatisSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgMybatisSpringBootLibraryAccessors laccForOrgMybatisSpringBootLibraryAccessors = new OrgMybatisSpringBootLibraryAccessors(owner);

        public OrgMybatisSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.mybatis.spring.boot</b>
         */
        public OrgMybatisSpringBootLibraryAccessors getBoot() {
            return laccForOrgMybatisSpringBootLibraryAccessors;
        }

    }

    public static class OrgMybatisSpringBootLibraryAccessors extends SubDependencyFactory {
        private final OrgMybatisSpringBootMybatisLibraryAccessors laccForOrgMybatisSpringBootMybatisLibraryAccessors = new OrgMybatisSpringBootMybatisLibraryAccessors(owner);

        public OrgMybatisSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.mybatis.spring.boot.mybatis</b>
         */
        public OrgMybatisSpringBootMybatisLibraryAccessors getMybatis() {
            return laccForOrgMybatisSpringBootMybatisLibraryAccessors;
        }

    }

    public static class OrgMybatisSpringBootMybatisLibraryAccessors extends SubDependencyFactory {
        private final OrgMybatisSpringBootMybatisSpringLibraryAccessors laccForOrgMybatisSpringBootMybatisSpringLibraryAccessors = new OrgMybatisSpringBootMybatisSpringLibraryAccessors(owner);

        public OrgMybatisSpringBootMybatisLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.mybatis.spring.boot.mybatis.spring</b>
         */
        public OrgMybatisSpringBootMybatisSpringLibraryAccessors getSpring() {
            return laccForOrgMybatisSpringBootMybatisSpringLibraryAccessors;
        }

    }

    public static class OrgMybatisSpringBootMybatisSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgMybatisSpringBootMybatisSpringBootLibraryAccessors laccForOrgMybatisSpringBootMybatisSpringBootLibraryAccessors = new OrgMybatisSpringBootMybatisSpringBootLibraryAccessors(owner);

        public OrgMybatisSpringBootMybatisSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.mybatis.spring.boot.mybatis.spring.boot</b>
         */
        public OrgMybatisSpringBootMybatisSpringBootLibraryAccessors getBoot() {
            return laccForOrgMybatisSpringBootMybatisSpringBootLibraryAccessors;
        }

    }

    public static class OrgMybatisSpringBootMybatisSpringBootLibraryAccessors extends SubDependencyFactory {

        public OrgMybatisSpringBootMybatisSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>starter</b> with <b>org.mybatis.spring.boot:mybatis-spring-boot-starter</b> coordinates and
         * with version reference <b>org.mybatis.spring.boot.mybatis.spring.boot.starter</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStarter() {
            return create("org.mybatis.spring.boot.mybatis.spring.boot.starter");
        }

    }

    public static class OrgProjectlombokLibraryAccessors extends SubDependencyFactory {

        public OrgProjectlombokLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lombok</b> with <b>org.projectlombok:lombok</b> coordinates and
         * with version reference <b>org.projectlombok.lombok</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLombok() {
            return create("org.projectlombok.lombok");
        }

    }

    public static class OrgSpringdocLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocLibraryAccessors laccForOrgSpringdocSpringdocLibraryAccessors = new OrgSpringdocSpringdocLibraryAccessors(owner);

        public OrgSpringdocLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springdoc.springdoc</b>
         */
        public OrgSpringdocSpringdocLibraryAccessors getSpringdoc() {
            return laccForOrgSpringdocSpringdocLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocOpenapiLibraryAccessors laccForOrgSpringdocSpringdocOpenapiLibraryAccessors = new OrgSpringdocSpringdocOpenapiLibraryAccessors(owner);

        public OrgSpringdocSpringdocLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springdoc.springdoc.openapi</b>
         */
        public OrgSpringdocSpringdocOpenapiLibraryAccessors getOpenapi() {
            return laccForOrgSpringdocSpringdocOpenapiLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocOpenapiStarterLibraryAccessors laccForOrgSpringdocSpringdocOpenapiStarterLibraryAccessors = new OrgSpringdocSpringdocOpenapiStarterLibraryAccessors(owner);

        public OrgSpringdocSpringdocOpenapiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springdoc.springdoc.openapi.starter</b>
         */
        public OrgSpringdocSpringdocOpenapiStarterLibraryAccessors getStarter() {
            return laccForOrgSpringdocSpringdocOpenapiStarterLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors laccForOrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors = new OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors(owner);

        public OrgSpringdocSpringdocOpenapiStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springdoc.springdoc.openapi.starter.webmvc</b>
         */
        public OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors getWebmvc() {
            return laccForOrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors extends SubDependencyFactory {

        public OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ui</b> with <b>org.springdoc:springdoc-openapi-starter-webmvc-ui</b> coordinates and
         * with version reference <b>org.springdoc.springdoc.openapi.starter.webmvc.ui</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUi() {
            return create("org.springdoc.springdoc.openapi.starter.webmvc.ui");
        }

    }

    public static class OrgSpringframeworkLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootLibraryAccessors laccForOrgSpringframeworkBootLibraryAccessors = new OrgSpringframeworkBootLibraryAccessors(owner);
        private final OrgSpringframeworkSecurityLibraryAccessors laccForOrgSpringframeworkSecurityLibraryAccessors = new OrgSpringframeworkSecurityLibraryAccessors(owner);

        public OrgSpringframeworkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.boot</b>
         */
        public OrgSpringframeworkBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.springframework.security</b>
         */
        public OrgSpringframeworkSecurityLibraryAccessors getSecurity() {
            return laccForOrgSpringframeworkSecurityLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringLibraryAccessors laccForOrgSpringframeworkBootSpringLibraryAccessors = new OrgSpringframeworkBootSpringLibraryAccessors(owner);

        public OrgSpringframeworkBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.boot.spring</b>
         */
        public OrgSpringframeworkBootSpringLibraryAccessors getSpring() {
            return laccForOrgSpringframeworkBootSpringLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootLibraryAccessors laccForOrgSpringframeworkBootSpringBootLibraryAccessors = new OrgSpringframeworkBootSpringBootLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot</b>
         */
        public OrgSpringframeworkBootSpringBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootSpringBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>devtools</b> with <b>org.springframework.boot:spring-boot-devtools</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.devtools</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDevtools() {
            return create("org.springframework.boot.spring.boot.devtools");
        }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot.starter</b>
         */
        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors getStarter() {
            return laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(owner);
        private final OrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors = new OrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>aop</b> with <b>org.springframework.boot:spring-boot-starter-aop</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.aop</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAop() {
            return create("org.springframework.boot.spring.boot.starter.aop");
        }

        /**
         * Dependency provider for <b>batch</b> with <b>org.springframework.boot:spring-boot-starter-batch</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.batch</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBatch() {
            return create("org.springframework.boot.spring.boot.starter.batch");
        }

        /**
         * Dependency provider for <b>security</b> with <b>org.springframework.boot:spring-boot-starter-security</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.security</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSecurity() {
            return create("org.springframework.boot.spring.boot.starter.security");
        }

        /**
         * Dependency provider for <b>test</b> with <b>org.springframework.boot:spring-boot-starter-test</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.test</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTest() {
            return create("org.springframework.boot.spring.boot.starter.test");
        }

        /**
         * Dependency provider for <b>tomcat</b> with <b>org.springframework.boot:spring-boot-starter-tomcat</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.tomcat</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTomcat() {
            return create("org.springframework.boot.spring.boot.starter.tomcat");
        }

        /**
         * Dependency provider for <b>validation</b> with <b>org.springframework.boot:spring-boot-starter-validation</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.validation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getValidation() {
            return create("org.springframework.boot.spring.boot.starter.validation");
        }

        /**
         * Dependency provider for <b>web</b> with <b>org.springframework.boot:spring-boot-starter-web</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.web</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getWeb() {
            return create("org.springframework.boot.spring.boot.starter.web");
        }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot.starter.data</b>
         */
        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors getData() {
            return laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot.starter.oauth2</b>
         */
        public OrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors getOauth2() {
            return laccForOrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>jpa</b> with <b>org.springframework.boot:spring-boot-starter-data-jpa</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.data.jpa</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJpa() {
            return create("org.springframework.boot.spring.boot.starter.data.jpa");
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkBootSpringBootStarterOauth2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>client</b> with <b>org.springframework.boot:spring-boot-starter-oauth2-client</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.oauth2.client</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getClient() {
            return create("org.springframework.boot.spring.boot.starter.oauth2.client");
        }

    }

    public static class OrgSpringframeworkSecurityLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkSecuritySpringLibraryAccessors laccForOrgSpringframeworkSecuritySpringLibraryAccessors = new OrgSpringframeworkSecuritySpringLibraryAccessors(owner);

        public OrgSpringframeworkSecurityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.security.spring</b>
         */
        public OrgSpringframeworkSecuritySpringLibraryAccessors getSpring() {
            return laccForOrgSpringframeworkSecuritySpringLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkSecuritySpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkSecuritySpringSecurityLibraryAccessors laccForOrgSpringframeworkSecuritySpringSecurityLibraryAccessors = new OrgSpringframeworkSecuritySpringSecurityLibraryAccessors(owner);

        public OrgSpringframeworkSecuritySpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.security.spring.security</b>
         */
        public OrgSpringframeworkSecuritySpringSecurityLibraryAccessors getSecurity() {
            return laccForOrgSpringframeworkSecuritySpringSecurityLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkSecuritySpringSecurityLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkSecuritySpringSecurityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>taglibs</b> with <b>org.springframework.security:spring-security-taglibs</b> coordinates and
         * with version reference <b>org.springframework.security.spring.security.taglibs</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTaglibs() {
            return create("org.springframework.security.spring.security.taglibs");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ChVersionAccessors vaccForChVersionAccessors = new ChVersionAccessors(providers, config);
        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final JakartaVersionAccessors vaccForJakartaVersionAccessors = new JakartaVersionAccessors(providers, config);
        private final MysqlVersionAccessors vaccForMysqlVersionAccessors = new MysqlVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.ch</b>
         */
        public ChVersionAccessors getCh() {
            return vaccForChVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.jakarta</b>
         */
        public JakartaVersionAccessors getJakarta() {
            return vaccForJakartaVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.mysql</b>
         */
        public MysqlVersionAccessors getMysql() {
            return vaccForMysqlVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class ChVersionAccessors extends VersionFactory  {

        private final ChQosVersionAccessors vaccForChQosVersionAccessors = new ChQosVersionAccessors(providers, config);
        public ChVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.ch.qos</b>
         */
        public ChQosVersionAccessors getQos() {
            return vaccForChQosVersionAccessors;
        }

    }

    public static class ChQosVersionAccessors extends VersionFactory  {

        private final ChQosLogbackVersionAccessors vaccForChQosLogbackVersionAccessors = new ChQosLogbackVersionAccessors(providers, config);
        public ChQosVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.ch.qos.logback</b>
         */
        public ChQosLogbackVersionAccessors getLogback() {
            return vaccForChQosLogbackVersionAccessors;
        }

    }

    public static class ChQosLogbackVersionAccessors extends VersionFactory  {

        private final ChQosLogbackLogbackVersionAccessors vaccForChQosLogbackLogbackVersionAccessors = new ChQosLogbackLogbackVersionAccessors(providers, config);
        public ChQosLogbackVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.ch.qos.logback.logback</b>
         */
        public ChQosLogbackLogbackVersionAccessors getLogback() {
            return vaccForChQosLogbackLogbackVersionAccessors;
        }

    }

    public static class ChQosLogbackLogbackVersionAccessors extends VersionFactory  {

        public ChQosLogbackLogbackVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>ch.qos.logback.logback.classic</b> with value <b>1.4.11</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getClassic() { return getVersion("ch.qos.logback.logback.classic"); }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComGooglecodeVersionAccessors vaccForComGooglecodeVersionAccessors = new ComGooglecodeVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.googlecode</b>
         */
        public ComGooglecodeVersionAccessors getGooglecode() {
            return vaccForComGooglecodeVersionAccessors;
        }

    }

    public static class ComGooglecodeVersionAccessors extends VersionFactory  {

        private final ComGooglecodeJsonVersionAccessors vaccForComGooglecodeJsonVersionAccessors = new ComGooglecodeJsonVersionAccessors(providers, config);
        public ComGooglecodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.googlecode.json</b>
         */
        public ComGooglecodeJsonVersionAccessors getJson() {
            return vaccForComGooglecodeJsonVersionAccessors;
        }

    }

    public static class ComGooglecodeJsonVersionAccessors extends VersionFactory  {

        private final ComGooglecodeJsonSimpleVersionAccessors vaccForComGooglecodeJsonSimpleVersionAccessors = new ComGooglecodeJsonSimpleVersionAccessors(providers, config);
        public ComGooglecodeJsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.googlecode.json.simple</b>
         */
        public ComGooglecodeJsonSimpleVersionAccessors getSimple() {
            return vaccForComGooglecodeJsonSimpleVersionAccessors;
        }

    }

    public static class ComGooglecodeJsonSimpleVersionAccessors extends VersionFactory  {

        private final ComGooglecodeJsonSimpleJsonVersionAccessors vaccForComGooglecodeJsonSimpleJsonVersionAccessors = new ComGooglecodeJsonSimpleJsonVersionAccessors(providers, config);
        public ComGooglecodeJsonSimpleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.googlecode.json.simple.json</b>
         */
        public ComGooglecodeJsonSimpleJsonVersionAccessors getJson() {
            return vaccForComGooglecodeJsonSimpleJsonVersionAccessors;
        }

    }

    public static class ComGooglecodeJsonSimpleJsonVersionAccessors extends VersionFactory  {

        public ComGooglecodeJsonSimpleJsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.googlecode.json.simple.json.simple</b> with value <b>1.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSimple() { return getVersion("com.googlecode.json.simple.json.simple"); }

    }

    public static class JakartaVersionAccessors extends VersionFactory  {

        private final JakartaServletVersionAccessors vaccForJakartaServletVersionAccessors = new JakartaServletVersionAccessors(providers, config);
        public JakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet</b>
         */
        public JakartaServletVersionAccessors getServlet() {
            return vaccForJakartaServletVersionAccessors;
        }

    }

    public static class JakartaServletVersionAccessors extends VersionFactory  {

        private final JakartaServletJspVersionAccessors vaccForJakartaServletJspVersionAccessors = new JakartaServletJspVersionAccessors(providers, config);
        public JakartaServletVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet.jsp</b>
         */
        public JakartaServletJspVersionAccessors getJsp() {
            return vaccForJakartaServletJspVersionAccessors;
        }

    }

    public static class JakartaServletJspVersionAccessors extends VersionFactory  {

        private final JakartaServletJspJstlVersionAccessors vaccForJakartaServletJspJstlVersionAccessors = new JakartaServletJspJstlVersionAccessors(providers, config);
        public JakartaServletJspVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet.jsp.jstl</b>
         */
        public JakartaServletJspJstlVersionAccessors getJstl() {
            return vaccForJakartaServletJspJstlVersionAccessors;
        }

    }

    public static class JakartaServletJspJstlVersionAccessors extends VersionFactory  {

        private final JakartaServletJspJstlJakartaVersionAccessors vaccForJakartaServletJspJstlJakartaVersionAccessors = new JakartaServletJspJstlJakartaVersionAccessors(providers, config);
        public JakartaServletJspJstlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet.jsp.jstl.jakarta</b>
         */
        public JakartaServletJspJstlJakartaVersionAccessors getJakarta() {
            return vaccForJakartaServletJspJstlJakartaVersionAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaVersionAccessors extends VersionFactory  {

        private final JakartaServletJspJstlJakartaServletVersionAccessors vaccForJakartaServletJspJstlJakartaServletVersionAccessors = new JakartaServletJspJstlJakartaServletVersionAccessors(providers, config);
        public JakartaServletJspJstlJakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet.jsp.jstl.jakarta.servlet</b>
         */
        public JakartaServletJspJstlJakartaServletVersionAccessors getServlet() {
            return vaccForJakartaServletJspJstlJakartaServletVersionAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaServletVersionAccessors extends VersionFactory  {

        private final JakartaServletJspJstlJakartaServletJspVersionAccessors vaccForJakartaServletJspJstlJakartaServletJspVersionAccessors = new JakartaServletJspJstlJakartaServletJspVersionAccessors(providers, config);
        public JakartaServletJspJstlJakartaServletVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet.jsp.jstl.jakarta.servlet.jsp</b>
         */
        public JakartaServletJspJstlJakartaServletJspVersionAccessors getJsp() {
            return vaccForJakartaServletJspJstlJakartaServletJspVersionAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaServletJspVersionAccessors extends VersionFactory  {

        private final JakartaServletJspJstlJakartaServletJspJstlVersionAccessors vaccForJakartaServletJspJstlJakartaServletJspJstlVersionAccessors = new JakartaServletJspJstlJakartaServletJspJstlVersionAccessors(providers, config);
        public JakartaServletJspJstlJakartaServletJspVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl</b>
         */
        public JakartaServletJspJstlJakartaServletJspJstlVersionAccessors getJstl() {
            return vaccForJakartaServletJspJstlJakartaServletJspJstlVersionAccessors;
        }

    }

    public static class JakartaServletJspJstlJakartaServletJspJstlVersionAccessors extends VersionFactory  {

        public JakartaServletJspJstlJakartaServletJspJstlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl.api</b> with value <b>2.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl.api"); }

    }

    public static class MysqlVersionAccessors extends VersionFactory  {

        private final MysqlMysqlVersionAccessors vaccForMysqlMysqlVersionAccessors = new MysqlMysqlVersionAccessors(providers, config);
        public MysqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.mysql.mysql</b>
         */
        public MysqlMysqlVersionAccessors getMysql() {
            return vaccForMysqlMysqlVersionAccessors;
        }

    }

    public static class MysqlMysqlVersionAccessors extends VersionFactory  {

        private final MysqlMysqlConnectorVersionAccessors vaccForMysqlMysqlConnectorVersionAccessors = new MysqlMysqlConnectorVersionAccessors(providers, config);
        public MysqlMysqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.mysql.mysql.connector</b>
         */
        public MysqlMysqlConnectorVersionAccessors getConnector() {
            return vaccForMysqlMysqlConnectorVersionAccessors;
        }

    }

    public static class MysqlMysqlConnectorVersionAccessors extends VersionFactory  {

        public MysqlMysqlConnectorVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>mysql.mysql.connector.java</b> with value <b>8.0.22</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJava() { return getVersion("mysql.mysql.connector.java"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgApacheVersionAccessors vaccForOrgApacheVersionAccessors = new OrgApacheVersionAccessors(providers, config);
        private final OrgBgeeVersionAccessors vaccForOrgBgeeVersionAccessors = new OrgBgeeVersionAccessors(providers, config);
        private final OrgGlassfishVersionAccessors vaccForOrgGlassfishVersionAccessors = new OrgGlassfishVersionAccessors(providers, config);
        private final OrgMybatisVersionAccessors vaccForOrgMybatisVersionAccessors = new OrgMybatisVersionAccessors(providers, config);
        private final OrgProjectlombokVersionAccessors vaccForOrgProjectlombokVersionAccessors = new OrgProjectlombokVersionAccessors(providers, config);
        private final OrgSpringdocVersionAccessors vaccForOrgSpringdocVersionAccessors = new OrgSpringdocVersionAccessors(providers, config);
        private final OrgSpringframeworkVersionAccessors vaccForOrgSpringframeworkVersionAccessors = new OrgSpringframeworkVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache</b>
         */
        public OrgApacheVersionAccessors getApache() {
            return vaccForOrgApacheVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.bgee</b>
         */
        public OrgBgeeVersionAccessors getBgee() {
            return vaccForOrgBgeeVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.glassfish</b>
         */
        public OrgGlassfishVersionAccessors getGlassfish() {
            return vaccForOrgGlassfishVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.mybatis</b>
         */
        public OrgMybatisVersionAccessors getMybatis() {
            return vaccForOrgMybatisVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.projectlombok</b>
         */
        public OrgProjectlombokVersionAccessors getProjectlombok() {
            return vaccForOrgProjectlombokVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.springdoc</b>
         */
        public OrgSpringdocVersionAccessors getSpringdoc() {
            return vaccForOrgSpringdocVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.springframework</b>
         */
        public OrgSpringframeworkVersionAccessors getSpringframework() {
            return vaccForOrgSpringframeworkVersionAccessors;
        }

    }

    public static class OrgApacheVersionAccessors extends VersionFactory  {

        private final OrgApacheLoggingVersionAccessors vaccForOrgApacheLoggingVersionAccessors = new OrgApacheLoggingVersionAccessors(providers, config);
        private final OrgApachePoiVersionAccessors vaccForOrgApachePoiVersionAccessors = new OrgApachePoiVersionAccessors(providers, config);
        private final OrgApacheTomcatVersionAccessors vaccForOrgApacheTomcatVersionAccessors = new OrgApacheTomcatVersionAccessors(providers, config);
        public OrgApacheVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.logging</b>
         */
        public OrgApacheLoggingVersionAccessors getLogging() {
            return vaccForOrgApacheLoggingVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.apache.poi</b>
         */
        public OrgApachePoiVersionAccessors getPoi() {
            return vaccForOrgApachePoiVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.apache.tomcat</b>
         */
        public OrgApacheTomcatVersionAccessors getTomcat() {
            return vaccForOrgApacheTomcatVersionAccessors;
        }

    }

    public static class OrgApacheLoggingVersionAccessors extends VersionFactory  {

        private final OrgApacheLoggingLog4jVersionAccessors vaccForOrgApacheLoggingLog4jVersionAccessors = new OrgApacheLoggingLog4jVersionAccessors(providers, config);
        public OrgApacheLoggingVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.logging.log4j</b>
         */
        public OrgApacheLoggingLog4jVersionAccessors getLog4j() {
            return vaccForOrgApacheLoggingLog4jVersionAccessors;
        }

    }

    public static class OrgApacheLoggingLog4jVersionAccessors extends VersionFactory  {

        private final OrgApacheLoggingLog4jLog4jVersionAccessors vaccForOrgApacheLoggingLog4jLog4jVersionAccessors = new OrgApacheLoggingLog4jLog4jVersionAccessors(providers, config);
        public OrgApacheLoggingLog4jVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.logging.log4j.log4j</b>
         */
        public OrgApacheLoggingLog4jLog4jVersionAccessors getLog4j() {
            return vaccForOrgApacheLoggingLog4jLog4jVersionAccessors;
        }

    }

    public static class OrgApacheLoggingLog4jLog4jVersionAccessors extends VersionFactory  {

        public OrgApacheLoggingLog4jLog4jVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.apache.logging.log4j.log4j.core</b> with value <b>2.17.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getCore() { return getVersion("org.apache.logging.log4j.log4j.core"); }

    }

    public static class OrgApachePoiVersionAccessors extends VersionFactory  {

        private final OrgApachePoiPoiVersionAccessors vaccForOrgApachePoiPoiVersionAccessors = new OrgApachePoiPoiVersionAccessors(providers, config);
        public OrgApachePoiVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.poi.poi</b>
         */
        public OrgApachePoiPoiVersionAccessors getPoi() {
            return vaccForOrgApachePoiPoiVersionAccessors;
        }

    }

    public static class OrgApachePoiPoiVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        public OrgApachePoiPoiVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.apache.poi.poi</b> with value <b>4.1.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("org.apache.poi.poi"); }

        /**
         * Version alias <b>org.apache.poi.poi.ooxml</b> with value <b>4.1.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getOoxml() { return getVersion("org.apache.poi.poi.ooxml"); }

    }

    public static class OrgApacheTomcatVersionAccessors extends VersionFactory  {

        private final OrgApacheTomcatEmbedVersionAccessors vaccForOrgApacheTomcatEmbedVersionAccessors = new OrgApacheTomcatEmbedVersionAccessors(providers, config);
        public OrgApacheTomcatVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.tomcat.embed</b>
         */
        public OrgApacheTomcatEmbedVersionAccessors getEmbed() {
            return vaccForOrgApacheTomcatEmbedVersionAccessors;
        }

    }

    public static class OrgApacheTomcatEmbedVersionAccessors extends VersionFactory  {

        private final OrgApacheTomcatEmbedTomcatVersionAccessors vaccForOrgApacheTomcatEmbedTomcatVersionAccessors = new OrgApacheTomcatEmbedTomcatVersionAccessors(providers, config);
        public OrgApacheTomcatEmbedVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.tomcat.embed.tomcat</b>
         */
        public OrgApacheTomcatEmbedTomcatVersionAccessors getTomcat() {
            return vaccForOrgApacheTomcatEmbedTomcatVersionAccessors;
        }

    }

    public static class OrgApacheTomcatEmbedTomcatVersionAccessors extends VersionFactory  {

        private final OrgApacheTomcatEmbedTomcatEmbedVersionAccessors vaccForOrgApacheTomcatEmbedTomcatEmbedVersionAccessors = new OrgApacheTomcatEmbedTomcatEmbedVersionAccessors(providers, config);
        public OrgApacheTomcatEmbedTomcatVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.tomcat.embed.tomcat.embed</b>
         */
        public OrgApacheTomcatEmbedTomcatEmbedVersionAccessors getEmbed() {
            return vaccForOrgApacheTomcatEmbedTomcatEmbedVersionAccessors;
        }

    }

    public static class OrgApacheTomcatEmbedTomcatEmbedVersionAccessors extends VersionFactory  {

        public OrgApacheTomcatEmbedTomcatEmbedVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.apache.tomcat.embed.tomcat.embed.jasper</b> with value <b>10.1.33</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJasper() { return getVersion("org.apache.tomcat.embed.tomcat.embed.jasper"); }

    }

    public static class OrgBgeeVersionAccessors extends VersionFactory  {

        private final OrgBgeeLog4jdbcVersionAccessors vaccForOrgBgeeLog4jdbcVersionAccessors = new OrgBgeeLog4jdbcVersionAccessors(providers, config);
        public OrgBgeeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bgee.log4jdbc</b>
         */
        public OrgBgeeLog4jdbcVersionAccessors getLog4jdbc() {
            return vaccForOrgBgeeLog4jdbcVersionAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcVersionAccessors extends VersionFactory  {

        private final OrgBgeeLog4jdbcLog4j2VersionAccessors vaccForOrgBgeeLog4jdbcLog4j2VersionAccessors = new OrgBgeeLog4jdbcLog4j2VersionAccessors(providers, config);
        public OrgBgeeLog4jdbcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bgee.log4jdbc.log4j2</b>
         */
        public OrgBgeeLog4jdbcLog4j2VersionAccessors getLog4j2() {
            return vaccForOrgBgeeLog4jdbcLog4j2VersionAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2VersionAccessors extends VersionFactory  {

        private final OrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors vaccForOrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors = new OrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors(providers, config);
        public OrgBgeeLog4jdbcLog4j2VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bgee.log4jdbc.log4j2.log4jdbc</b>
         */
        public OrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors getLog4jdbc() {
            return vaccForOrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors extends VersionFactory  {

        private final OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors vaccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors = new OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors(providers, config);
        public OrgBgeeLog4jdbcLog4j2Log4jdbcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bgee.log4jdbc.log4j2.log4jdbc.log4j2</b>
         */
        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors getLog4j2() {
            return vaccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors extends VersionFactory  {

        private final OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors vaccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors = new OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors(providers, config);
        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bgee.log4jdbc.log4j2.log4jdbc.log4j2.jdbc4</b>
         */
        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors getJdbc4() {
            return vaccForOrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors;
        }

    }

    public static class OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors extends VersionFactory  {

        public OrgBgeeLog4jdbcLog4j2Log4jdbcLog4j2Jdbc4VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.bgee.log4jdbc.log4j2.log4jdbc.log4j2.jdbc4.v1</b> with value <b>1.16</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getV1() { return getVersion("org.bgee.log4jdbc.log4j2.log4jdbc.log4j2.jdbc4.v1"); }

    }

    public static class OrgGlassfishVersionAccessors extends VersionFactory  {

        private final OrgGlassfishWebVersionAccessors vaccForOrgGlassfishWebVersionAccessors = new OrgGlassfishWebVersionAccessors(providers, config);
        public OrgGlassfishVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.glassfish.web</b>
         */
        public OrgGlassfishWebVersionAccessors getWeb() {
            return vaccForOrgGlassfishWebVersionAccessors;
        }

    }

    public static class OrgGlassfishWebVersionAccessors extends VersionFactory  {

        private final OrgGlassfishWebJakartaVersionAccessors vaccForOrgGlassfishWebJakartaVersionAccessors = new OrgGlassfishWebJakartaVersionAccessors(providers, config);
        public OrgGlassfishWebVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.glassfish.web.jakarta</b>
         */
        public OrgGlassfishWebJakartaVersionAccessors getJakarta() {
            return vaccForOrgGlassfishWebJakartaVersionAccessors;
        }

    }

    public static class OrgGlassfishWebJakartaVersionAccessors extends VersionFactory  {

        private final OrgGlassfishWebJakartaServletVersionAccessors vaccForOrgGlassfishWebJakartaServletVersionAccessors = new OrgGlassfishWebJakartaServletVersionAccessors(providers, config);
        public OrgGlassfishWebJakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.glassfish.web.jakarta.servlet</b>
         */
        public OrgGlassfishWebJakartaServletVersionAccessors getServlet() {
            return vaccForOrgGlassfishWebJakartaServletVersionAccessors;
        }

    }

    public static class OrgGlassfishWebJakartaServletVersionAccessors extends VersionFactory  {

        private final OrgGlassfishWebJakartaServletJspVersionAccessors vaccForOrgGlassfishWebJakartaServletJspVersionAccessors = new OrgGlassfishWebJakartaServletJspVersionAccessors(providers, config);
        public OrgGlassfishWebJakartaServletVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.glassfish.web.jakarta.servlet.jsp</b>
         */
        public OrgGlassfishWebJakartaServletJspVersionAccessors getJsp() {
            return vaccForOrgGlassfishWebJakartaServletJspVersionAccessors;
        }

    }

    public static class OrgGlassfishWebJakartaServletJspVersionAccessors extends VersionFactory  {

        public OrgGlassfishWebJakartaServletJspVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.glassfish.web.jakarta.servlet.jsp.jstl</b> with value <b>2.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJstl() { return getVersion("org.glassfish.web.jakarta.servlet.jsp.jstl"); }

    }

    public static class OrgMybatisVersionAccessors extends VersionFactory  {

        private final OrgMybatisSpringVersionAccessors vaccForOrgMybatisSpringVersionAccessors = new OrgMybatisSpringVersionAccessors(providers, config);
        public OrgMybatisVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.mybatis.spring</b>
         */
        public OrgMybatisSpringVersionAccessors getSpring() {
            return vaccForOrgMybatisSpringVersionAccessors;
        }

    }

    public static class OrgMybatisSpringVersionAccessors extends VersionFactory  {

        private final OrgMybatisSpringBootVersionAccessors vaccForOrgMybatisSpringBootVersionAccessors = new OrgMybatisSpringBootVersionAccessors(providers, config);
        public OrgMybatisSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.mybatis.spring.boot</b>
         */
        public OrgMybatisSpringBootVersionAccessors getBoot() {
            return vaccForOrgMybatisSpringBootVersionAccessors;
        }

    }

    public static class OrgMybatisSpringBootVersionAccessors extends VersionFactory  {

        private final OrgMybatisSpringBootMybatisVersionAccessors vaccForOrgMybatisSpringBootMybatisVersionAccessors = new OrgMybatisSpringBootMybatisVersionAccessors(providers, config);
        public OrgMybatisSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.mybatis.spring.boot.mybatis</b>
         */
        public OrgMybatisSpringBootMybatisVersionAccessors getMybatis() {
            return vaccForOrgMybatisSpringBootMybatisVersionAccessors;
        }

    }

    public static class OrgMybatisSpringBootMybatisVersionAccessors extends VersionFactory  {

        private final OrgMybatisSpringBootMybatisSpringVersionAccessors vaccForOrgMybatisSpringBootMybatisSpringVersionAccessors = new OrgMybatisSpringBootMybatisSpringVersionAccessors(providers, config);
        public OrgMybatisSpringBootMybatisVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.mybatis.spring.boot.mybatis.spring</b>
         */
        public OrgMybatisSpringBootMybatisSpringVersionAccessors getSpring() {
            return vaccForOrgMybatisSpringBootMybatisSpringVersionAccessors;
        }

    }

    public static class OrgMybatisSpringBootMybatisSpringVersionAccessors extends VersionFactory  {

        private final OrgMybatisSpringBootMybatisSpringBootVersionAccessors vaccForOrgMybatisSpringBootMybatisSpringBootVersionAccessors = new OrgMybatisSpringBootMybatisSpringBootVersionAccessors(providers, config);
        public OrgMybatisSpringBootMybatisSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.mybatis.spring.boot.mybatis.spring.boot</b>
         */
        public OrgMybatisSpringBootMybatisSpringBootVersionAccessors getBoot() {
            return vaccForOrgMybatisSpringBootMybatisSpringBootVersionAccessors;
        }

    }

    public static class OrgMybatisSpringBootMybatisSpringBootVersionAccessors extends VersionFactory  {

        public OrgMybatisSpringBootMybatisSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.mybatis.spring.boot.mybatis.spring.boot.starter</b> with value <b>3.0.4</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getStarter() { return getVersion("org.mybatis.spring.boot.mybatis.spring.boot.starter"); }

    }

    public static class OrgProjectlombokVersionAccessors extends VersionFactory  {

        public OrgProjectlombokVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.projectlombok.lombok</b> with value <b>1.18.28</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLombok() { return getVersion("org.projectlombok.lombok"); }

    }

    public static class OrgSpringdocVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocVersionAccessors vaccForOrgSpringdocSpringdocVersionAccessors = new OrgSpringdocSpringdocVersionAccessors(providers, config);
        public OrgSpringdocVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springdoc.springdoc</b>
         */
        public OrgSpringdocSpringdocVersionAccessors getSpringdoc() {
            return vaccForOrgSpringdocSpringdocVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocOpenapiVersionAccessors vaccForOrgSpringdocSpringdocOpenapiVersionAccessors = new OrgSpringdocSpringdocOpenapiVersionAccessors(providers, config);
        public OrgSpringdocSpringdocVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springdoc.springdoc.openapi</b>
         */
        public OrgSpringdocSpringdocOpenapiVersionAccessors getOpenapi() {
            return vaccForOrgSpringdocSpringdocOpenapiVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocOpenapiStarterVersionAccessors vaccForOrgSpringdocSpringdocOpenapiStarterVersionAccessors = new OrgSpringdocSpringdocOpenapiStarterVersionAccessors(providers, config);
        public OrgSpringdocSpringdocOpenapiVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springdoc.springdoc.openapi.starter</b>
         */
        public OrgSpringdocSpringdocOpenapiStarterVersionAccessors getStarter() {
            return vaccForOrgSpringdocSpringdocOpenapiStarterVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors vaccForOrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors = new OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors(providers, config);
        public OrgSpringdocSpringdocOpenapiStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springdoc.springdoc.openapi.starter.webmvc</b>
         */
        public OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors getWebmvc() {
            return vaccForOrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors extends VersionFactory  {

        public OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springdoc.springdoc.openapi.starter.webmvc.ui</b> with value <b>2.1.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getUi() { return getVersion("org.springdoc.springdoc.openapi.starter.webmvc.ui"); }

    }

    public static class OrgSpringframeworkVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootVersionAccessors vaccForOrgSpringframeworkBootVersionAccessors = new OrgSpringframeworkBootVersionAccessors(providers, config);
        private final OrgSpringframeworkSecurityVersionAccessors vaccForOrgSpringframeworkSecurityVersionAccessors = new OrgSpringframeworkSecurityVersionAccessors(providers, config);
        public OrgSpringframeworkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.boot</b>
         */
        public OrgSpringframeworkBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.springframework.security</b>
         */
        public OrgSpringframeworkSecurityVersionAccessors getSecurity() {
            return vaccForOrgSpringframeworkSecurityVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringVersionAccessors vaccForOrgSpringframeworkBootSpringVersionAccessors = new OrgSpringframeworkBootSpringVersionAccessors(providers, config);
        public OrgSpringframeworkBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring</b>
         */
        public OrgSpringframeworkBootSpringVersionAccessors getSpring() {
            return vaccForOrgSpringframeworkBootSpringVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootVersionAccessors vaccForOrgSpringframeworkBootSpringBootVersionAccessors = new OrgSpringframeworkBootSpringBootVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot</b>
         */
        public OrgSpringframeworkBootSpringBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootSpringBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors = new OrgSpringframeworkBootSpringBootStarterVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.devtools</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getDevtools() { return getVersion("org.springframework.boot.spring.boot.devtools"); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot.starter</b>
         */
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors getStarter() {
            return vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterDataVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors = new OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(providers, config);
        private final OrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors = new OrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.aop</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAop() { return getVersion("org.springframework.boot.spring.boot.starter.aop"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.batch</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBatch() { return getVersion("org.springframework.boot.spring.boot.starter.batch"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.security</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSecurity() { return getVersion("org.springframework.boot.spring.boot.starter.security"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.test</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTest() { return getVersion("org.springframework.boot.spring.boot.starter.test"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.tomcat</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTomcat() { return getVersion("org.springframework.boot.spring.boot.starter.tomcat"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.validation</b> with value <b>3.1.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getValidation() { return getVersion("org.springframework.boot.spring.boot.starter.validation"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.web</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getWeb() { return getVersion("org.springframework.boot.spring.boot.starter.web"); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot.starter.data</b>
         */
        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors getData() {
            return vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot.starter.oauth2</b>
         */
        public OrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors getOauth2() {
            return vaccForOrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.data.jpa</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJpa() { return getVersion("org.springframework.boot.spring.boot.starter.data.jpa"); }

    }

    public static class OrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors extends VersionFactory  {

        public OrgSpringframeworkBootSpringBootStarterOauth2VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.oauth2.client</b> with value <b>3.4.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getClient() { return getVersion("org.springframework.boot.spring.boot.starter.oauth2.client"); }

    }

    public static class OrgSpringframeworkSecurityVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkSecuritySpringVersionAccessors vaccForOrgSpringframeworkSecuritySpringVersionAccessors = new OrgSpringframeworkSecuritySpringVersionAccessors(providers, config);
        public OrgSpringframeworkSecurityVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.security.spring</b>
         */
        public OrgSpringframeworkSecuritySpringVersionAccessors getSpring() {
            return vaccForOrgSpringframeworkSecuritySpringVersionAccessors;
        }

    }

    public static class OrgSpringframeworkSecuritySpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkSecuritySpringSecurityVersionAccessors vaccForOrgSpringframeworkSecuritySpringSecurityVersionAccessors = new OrgSpringframeworkSecuritySpringSecurityVersionAccessors(providers, config);
        public OrgSpringframeworkSecuritySpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.security.spring.security</b>
         */
        public OrgSpringframeworkSecuritySpringSecurityVersionAccessors getSecurity() {
            return vaccForOrgSpringframeworkSecuritySpringSecurityVersionAccessors;
        }

    }

    public static class OrgSpringframeworkSecuritySpringSecurityVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkSecuritySpringSecurityVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.security.spring.security.taglibs</b> with value <b>6.4.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTaglibs() { return getVersion("org.springframework.security.spring.security.taglibs"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
