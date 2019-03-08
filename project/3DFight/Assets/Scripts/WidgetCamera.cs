using UnityEngine;
using System.Collections;

//相机跟随人物
public class WidgetCamera : MonoBehaviour {

    public Transform target;  //目标物体
    public float distance = 10f;  //距离
    public float height = 5f;  //高度
    public float heightDamping = 2.0f;  //摄像机高度进行调整的阻尼
    public float rotationDamping = 3.0f;  //摄像机旋转进行调整的阻尼
    public float distanceDampingX = 0.5f;  //摄像机在X轴上进行调整的阻尼
    public float distanceDampingZ = 0.2f;  //摄像机在Z轴上进行调整的阻尼
    public float camSpeed = 2.0f;  //摄像机移动速度
    public bool smoothed = true;  //摄像机是否平滑

    private float wantedRotationAngle;  //摄像机要达到的角度
    private float wantedHeight;  //摄像机要达到的高度
    private float wantedDistanceZ;  //摄像机要达到的Z轴位置
    private float wantedDistanceX;  //摄像机要达到的X轴位置

    private float currentRotationAngle;  //摄像机当前的角度
    private float currentHeight;  //摄像机当前的高度
    private float currentDistanceZ;  //摄像机当前的Z轴位置
    private float currentDistanceX;  //摄像机当前的X轴位置

    private Quaternion currentRotation;  

    void LateUpdate()
    {
        if (!target)
        {
            return;
        }

        wantedRotationAngle = target.eulerAngles.y;  //取得当前摄像机要到达的角度
        wantedHeight = target.position.y + height;  //取得当前摄像机要到达的高度
        wantedDistanceZ = target.position.z - distance;  //取得当前摄像机要到达的Z轴位置
        wantedDistanceX = target.position.x - distance;  //取得当前摄像机要到达的X轴位置

        currentRotationAngle = transform.eulerAngles.y;  //获取当前角度位置
        currentHeight = transform.position.y;  //获取当前位置信息
        currentDistanceZ = transform.position.z;
        currentDistanceX = transform.position.x;

        currentRotationAngle = Mathf.LerpAngle(currentRotationAngle, wantedRotationAngle, rotationDamping * Time.deltaTime);  //平滑到新的角度，置为当前角度值
        currentHeight = Mathf.LerpAngle(currentHeight, wantedHeight, heightDamping * Time.deltaTime);
        currentDistanceZ = Mathf.LerpAngle(currentDistanceZ, wantedDistanceZ, distanceDampingZ * Time.deltaTime);
        currentDistanceX = Mathf.LerpAngle(currentDistanceX, wantedDistanceX, distanceDampingX * Time.deltaTime);

        currentRotation = Quaternion.Euler(0, currentRotationAngle, 0);  //从角度值转为角度

        transform.position -= currentRotation * Vector3.forward * distance;  //摄像机往目标移动
        transform.position = new Vector3(currentDistanceX, currentHeight, currentDistanceZ);  //不断更新位置

        LookAtMe();
    }

    void LookAtMe()
    {
        if (smoothed)
        {
            Quaternion camrotation = Quaternion.LookRotation(target.position - transform.position);
            transform.rotation = Quaternion.Slerp(transform.rotation, camrotation, Time.deltaTime * camSpeed);
        }
        else
        {
            transform.LookAt(target); //始终盯着目标物体
        }      
    }
}
