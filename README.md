# smart_tv_appium

# BrowserStack smartTV CE demo
This just has basic Appium-JAVA scripts which runs on the following smart TV devices on Browserstack:

- Amazon Fire TV Stick 4K (Android v7.1)
  
- Nvidia Shield TV Pro 2019 (Android v11.0)
  
- Apple TV 4k (tvOS v16.3)

## Install repo

---
- Clone the repo
- Set your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings) in the browserstack.yml files or set BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY as environmental variables
- This repo was designed to work with smart TV.  
- 
## You can run any of the following scenerios


1. Run a test on Amazon Fire TV Stick 4K
```
export BROWSERSTACK_CONFIG_FILE="src/resources/conf/browserstack-amazonFireTV.yml" 
mvn test -P androidFireTV
```
2. Run a test on nvidia_shieldTV
```
export BROWSERSTACK_CONFIG_FILE="src/resources/conf/browserstack-nvidia_shieldTV.yml"
mvn test -P nvidia_shieldTV
```
3. Run a test on appleTV
```
export BROWSERSTACK_CONFIG_FILE="src/resources/conf/browserstack-appleTV.yml"
mvn test -P appleTV
```

## Notes

---
- You can view your test results on the [BrowserStack app automate dashboard](https://app-automate.browserstack.com/dashboard/v2)

  
