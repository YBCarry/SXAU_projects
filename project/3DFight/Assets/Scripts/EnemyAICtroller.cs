using UnityEngine;
using System.Collections;

public class EnemyAICtroller : MonoBehaviour {

    public Transform target;
    private CharacterController characterController;
    private Animation animation;

    public float rotateSpeed = 30f;  //转向速度
    public float waleSpeed = 3f;  //等待状态的速度
    public float attackMoveSpeed = 5.0f;  //攻击时跑向主角的速度
    public float direcitonTraveltime = 2f;  //转向时间间隔
    private float timeToNewDireciton = 0f;  //计时器
    private float idleTime = 1.5f;  //转向思考时间

    public float attackDistance = 15f;  //攻击距离
    public Vector3 distanceToPlayer;  //巡逻范围
    private float attackRadius = 2.5f;  //攻击范围
    private float damage = 1f;  //造成伤害

    private Vector3 attackPosition = new Vector3(0, 1, 0); 
    private bool isAttacking = false;  //是否攻击
    private float lastAttackTime = 0f;  //攻击间隔时间

    void Start()
    {
        characterController = GetComponent<CharacterController>();
        if (!target)
        {
            target = GameObject.FindGameObjectWithTag("Player").transform;
        }
        animation = GetComponent<Animation>();
        animation.wrapMode = WrapMode.Loop;
        animation["EBunny_Death"].wrapMode = WrapMode.Once;
        animation["EBunny_Death"].layer = 5;
        animation["EBunny_Hit"].layer = 3;
        animation["EBunny_Attack"].layer = 1;

        StartCoroutine( InitEnemy());
    }

    //AI主要逻辑
    IEnumerator InitEnemy()
    {
        while (true)
        {
            yield return StartCoroutine( Idle());
            yield return StartCoroutine( Attack());
        }
    }

    //等待状态
    IEnumerator Idle()
    {
        while (true)
        {
            if (Time.time > timeToNewDireciton)  //到了转换方向的时候
            {
                yield return new WaitForSeconds(idleTime);  //等待idleTime时间，模拟思考行为

                //通过产生随机值，让怪物随机转换方向
                if (Random.value > 0.5)
                {
                    transform.Rotate(new Vector3(0, 5, 0), rotateSpeed);
                }
                else
                {
                    transform.Rotate(new Vector3(0, -5, 0), rotateSpeed);
                }
                timeToNewDireciton = Time.time + direcitonTraveltime;
            }

            Vector3 waleForWard = transform.TransformDirection(Vector3.forward);
            characterController.SimpleMove(waleForWard * waleSpeed);

            distanceToPlayer = transform.position - target.position;  //算出与目标的距离
            if (distanceToPlayer.magnitude < attackDistance)
            {
                yield break;
            }
            yield return null;
        }

    }

    //攻击状态
    IEnumerator Attack()
    {
        isAttacking = true;
        animation.Play("EBunny_Attack");
        transform.LookAt(target);

        Vector3 direction = transform.TransformDirection(Vector3.forward * attackMoveSpeed);
        characterController.SimpleMove(direction);

        bool lostSight = false;

        if (!lostSight)
        {
            Vector3 location = transform.TransformPoint(attackPosition) - target.position;  //计算与玩家的距离
            if (Time.time > lastAttackTime + 1.5f && location.magnitude < attackRadius)
            {
                target.SendMessage("ApplyDamage", damage);
                lastAttackTime = Time.time;
            }
            if (location.magnitude > attackRadius)
            {
                lostSight = true;
                yield break;
            }
            yield return null;
        }
        isAttacking = false;
    }
}
