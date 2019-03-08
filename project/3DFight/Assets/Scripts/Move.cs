using UnityEngine;
using System.Collections;

//控制角色运动（移动、跳跃、加速、半蹲）
public class Move : MonoBehaviour {

    public float MoveSpeed = 6.0f;  //物体的移动速度
    public float RotateSpeed = 4.0f;  //物体的旋转速度  
    public float jumpSpeed = 6f;  //跳的高度
    public float faseSpeed = 2f;  //加速速度
    public float gravity = 20f;  //重力
    public float duckSpeed = 0.5f;  //半蹲时的移动速度

    public CharacterController ctroller;

    public bool isControllable = true;  //角色是否可以被控制

    private Vector3 moveDirection = Vector3.zero;
    private Vector3 rotateDirection = Vector3.zero;

    private float moveHorz = 0f;

    private bool ground = false;  //是否在地面上
    private bool isBoosting = false; //是否跳跃
    private float duckHeight = 1.0f;  //半蹲高度
    private float normalHeight = 2.0f;  //正常高度
    private bool isDucking = false;  //是否半蹲


    private WidgetStatus widgetStatus;

    void Awake()
    {
        ctroller = GetComponent<CharacterController>();
        widgetStatus = GetComponent<WidgetStatus>();
    }


    void FixedUpdate()
    {
        if (!isControllable)
        {
            Input.ResetInputAxes();
        }
        else
        {
            if (ground)
            {
                float h = Input.GetAxis("Horizontal");
                float v = Input.GetAxis("Vertical");

                moveDirection = new Vector3(h, 0, v);
                moveDirection = transform.TransformDirection(moveDirection);
                moveDirection *= MoveSpeed;

                moveHorz = Input.GetAxis("Horizontal");

                if (moveHorz > 0)  //向右转
                {
                    rotateDirection = new Vector3(0, 1, 0);
                }
                else if (moveHorz < 0)  //向左转
                {
                    rotateDirection = new Vector3(0, -1, 0);
                }
                else
                {
                    rotateDirection = new Vector3(0, 0, 0);
                }

                if (Input.GetButton("Jump"))
                {
                    moveDirection.y = jumpSpeed;
                }
                if (Input.GetButton("Boost"))
                {
                    if (widgetStatus)
                    {
                        if (widgetStatus.boost > 0)
                        {
                            widgetStatus.boost -= widgetStatus.widgetUseBoost * Time.deltaTime;
                            isBoosting = true;
                        }
                    }
                    moveDirection *= faseSpeed;
                    isBoosting = true;
                }
                if (Input.GetButtonUp("Boost"))
                {
                    isBoosting = false;
                }

                if (Input.GetButton("Duck"))
                {
                    ctroller.height = duckHeight;
                    ctroller.center = new Vector3(ctroller.center.x, ctroller.height / 2 + 0.25f, ctroller.center.z);
                    moveDirection *= duckSpeed;
                    isDucking = true;
                }
                if (Input.GetButtonUp("Duck"))
                {
                    ctroller.height = normalHeight;
                    ctroller.center = new Vector3(ctroller.center.x, ctroller.height / 2, ctroller.center.z);
                    //moveDirection *= MoveSpeed;
                    isDucking = false;
                }

                if (Input.GetKeyUp(KeyCode.P))
                {
                    widgetStatus.ApplyDamage(3);
                }
                if (Input.GetKeyUp(KeyCode.O))
                {
                    widgetStatus.AddHealth(3);
                }
            }

            moveDirection.y -= gravity * Time.deltaTime;

            ctroller.transform.Rotate(rotateDirection * Time.deltaTime, RotateSpeed);
            CollisionFlags flags = ctroller.Move(moveDirection * Time.deltaTime);

            ground = ((flags & CollisionFlags.Below) != 0);
        }         
    }

    public bool IsMoving()
    {
        return moveDirection.magnitude > 0.5;
    }

    public bool IsDucking()
    {
        return isDucking;
    }

    public bool IsBoosting()
    {
        return isBoosting;
    }

    public bool IsGround()
    {
        return ground;
    }
}
