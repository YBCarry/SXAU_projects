using UnityEngine;
using System.Collections;

//人物动画
public class WidgetAnimation : MonoBehaviour {

    public Move move;
    public Animator animator;

    void Start()
    {
        move = GetComponent<Move>();
        animator = GetComponent<Animator>();

        if (animator.layerCount == 2)
        {
            animator.SetLayerWeight(1, 1);
        }
    }

    void Update()
    {
        if (move.IsGround())
        {
            animator.SetBool("isFallDown", false);
            if (move.IsBoosting())
            {
                animator.SetBool("isBoost", true);
            }
            else
            {
                animator.SetBool("isBoost", false);
            }

            if (move.IsDucking())
            {
                animator.SetBool("isDuck", true);
            }
            else
            {
                animator.SetBool("isDuck", false);
            }

            if (move.IsMoving())
            {
                animator.SetFloat("Speed", Input.GetAxis("Vertical"));
            }
            else
            {
                if (Input.GetButtonDown("Jump"))
                {
                    animator.SetBool("isJump", true);
                }
                if (Input.GetButtonUp("Jump"))
                {
                    animator.SetBool("isJump", false);
                }

                if (!move.IsGround())
                {
                    animator.SetBool("isFallDown", true);
                }
            }
        }
    }

    public void GetHit()
    {
        animator.SetBool("isGotHit", true);
    }

    public void playDie()
    {
        animator.SetBool("isDie", true);
    }

    public void ReBorn()
    {
        animator.SetBool("isDie", false);
    }
}
