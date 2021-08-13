To run web mobile tests use command 'mvn clean test -Pweb'

To run native mobile tests use 'mvn clean test -Pnative'

Cloud mobile test profiles: 
-PiosCloudWeb
-PiosCloudNative
-PcloudWeb
-PcloudNative

*For cloud mobile native tests EPAMTestApp should be installed on the device*

To run cloud mobile tests use command 'mvn clean test -P(cloud_test_profile) -Dapi.key=(your_api_key) -Dudid=(device_udid)'

*!Please, use long version of api-key*
