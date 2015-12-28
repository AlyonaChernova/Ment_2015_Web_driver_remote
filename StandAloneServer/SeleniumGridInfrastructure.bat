Start java -jar selenium-server-standalone-2.48.2.jar -role hub
Start java -Dwebdriver.chrome.driver=.\chromedriver.exe -jar selenium-server-standalone-2.48.2.jar -role node -hub http://10.12.13.16:4444/grid/register -browser browserName=chrome
Start java -jar selenium-server-standalone-2.48.2.jar -role node -port 5551 -hub http://10.12.13.16:4444/grid/register -browser browserName=firefox 
