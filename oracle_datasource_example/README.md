osgi:install -s wrap:mvn:com.oracle/ojdbc6/11.2.0.3

features:install jndi

features:install jdbc

features:install db-dependencies

jdbc:query jdbc/transactionsdb 'select * from oracle_table'

