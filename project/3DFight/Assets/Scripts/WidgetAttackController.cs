using UnityEngine;
using System.Collections;

//主角攻击控制
public class WidgetAttackController : MonoBehaviour {

    public Move move;
    public Animator animator;

    public float attackTime = 1.0f;  //发动攻击的时间
    public Vector3 attackPosition = new Vector3(0, 1, 0);
    public float attackRadius = 3f;  //发动攻击的半径
    public float daamge = 1f;  //攻击伤害值
    private float time = 0f;  //计时器
    private bool isBusy = false;  //是否发动攻击
    private Vector3 ourLocation;  //主角位置
    private GameObject[] enemies;  //怪物的数组

    public GameObject wangtedEnemy;

    void Start()
    {
        move = GetComponent<Move>();
        animator = GetComponent<Animator>();
    }

    void Update()
    {
        time += Time.deltaTime;

        if (!isBusy && Input.GetButtonDown("Attack") && time > attackTime)
        {
            StartCoroutine(DidAttack());
            isBusy = true;
            time = 0;  //计时器归零
        }
    }

    IEnumerator DidAttack()
    {
        animator.SetBool("isTaser", true);  //播放攻击动画
        yield return new WaitForSeconds(1.0f);
        animator.SetBool("isTaser", false);  //停止播放

        ourLocation = transform.TransformPoint(attackPosition);  //在主角的上边0， 1， 0的位置，取得一个点
        enemies = GameObject.FindGameObjectsWithTag("Enemy");  //取得周围的怪物

        foreach (GameObject enemy in enemies)
        {
            EBunnyStatus enemyStatus = enemy.GetComponent<EBunnyStatus>();
            if (enemyStatus == null)
            {
                continue;
            }
            if (Vector3.Distance(enemy.transform.position , ourLocation) < attackRadius)  //判断怪物是否进入攻击半径
            {
                enemyStatus.ApplyDamage(daamge);
            }
        }
        isBusy = false;
    }

    //取得距离主角最近的怪物
    public GameObject GetCloseEnemy()
    {
        enemies = GameObject.FindGameObjectsWithTag("Enemy");
        float distanceToEnemy = Mathf.Infinity;
        foreach (var enemy in enemies)
        {
            float newDistanceToEnemy = Vector3.Distance(enemy.transform.position, this.transform.position);
            if (newDistanceToEnemy < distanceToEnemy)
            {
                distanceToEnemy = newDistanceToEnemy;
                wangtedEnemy = enemy;
            }
        }

        return wangtedEnemy;
    }
}
