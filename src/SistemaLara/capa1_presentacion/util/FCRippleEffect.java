package SistemaLara.capa1_presentacion.util;




import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A {@code RippleEffect} is applied into certain components, like buttons and
 * certain list elements. Basically, is that wave of color that appears when you
 * click stuff.
 */
public class FCRippleEffect {
    private final List<RippleAnimation> ripples = new ArrayList<>();
    private final JComponent target;
    private final SwingTimerTimingSource timer;

    private FCRippleEffect(final JComponent component) {
        this.target = component;

        timer = new SwingTimerTimingSource();
        timer.init();
    }

    /**
     * Paints this effect. Each component is responsible of calling {@link
     * #paint(Graphics)} in order to display the effect. Here's an example of
     * how the ripple effect can be used:
     * <p/>
     * <code>
     * protected void paintComponent(Graphics g) {<br/>
     *     super.paintComponent(g);<br/>
     *     if (isEnabled()) {<br/>
     *          g.setClip(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));<br/>
     *          g.setColor(myRippleColor);<br/>
     *          ripple.paint(g);<br/>
     *     }<br/>
     * }
     * </code>
     * @param g canvas
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (RippleAnimation rippleAnimation : ripples) {
            float rippleOpacity = rippleAnimation.rippleOpacity.getValue().floatValue();
            Point rippleCenter = rippleAnimation.rippleCenter;
            int rippleRadius = rippleAnimation.rippleRadius.getValue();

            Color fg = g2.getColor();
            g2.setColor(new Color(fg.getRed() / 255f, fg.getGreen() / 255f, fg.getBlue() / 255f, rippleOpacity));
            g2.fillOval(rippleCenter.x - rippleRadius, rippleCenter.y - rippleRadius, 2 * rippleRadius, 2 * rippleRadius);
        }
    }

    /**
     * Adds a ripple at the given point.
     *
     * @param point     point to add the ripple at
     * @param maxRadius the maximum radius of the ripple
     */
    public void addRipple(Point point, int maxRadius) {
        final RippleAnimation ripple = new RippleAnimation(point, maxRadius);
        ripples.add(ripple);
        ripple.start();
    }

    /**
     * Creates a ripple effect for the given component. Each component is
     * responsible of calling {@link #paint(Graphics)} in order to display the
     * effect. Here's an example of how the ripple effect can be used:
     * <p/>
     * <code>
     * protected void paintComponent(Graphics g) {<br/>
     *     super.paintComponent(g);<br/>
     *     if (isEnabled()) {<br/>
     *          g.setClip(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));<br/>
     *          g.setColor(myRippleColor);<br/>
     *          ripple.paint(g);<br/>
     *     }<br/>
     * }
     * </code>
     * @param target target component
     * @return ripple effect for that component
     * @see MaterialButton for an example of how the ripple effect is used
     */
    public static FCRippleEffect applyTo(final JComponent target) {
        final FCRippleEffect rippleEffect = new FCRippleEffect(target);
        target.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rippleEffect.addRipple(e.getPoint(), target.getWidth());
            }
        });
        return rippleEffect;
    }

    /**
     * Creates a ripple effect for the given component that is limited to the
     * component's size and will always start in the center. Each component is
     * responsible of calling {@link #paint(Graphics)} in order to display the
     * effect. Here's an example of how the ripple effect can be used:
     * <p/>
     * <code>
     * protected void paintComponent(Graphics g) {<br/>
     *     super.paintComponent(g);<br/>
     *     if (isEnabled()) {<br/>
     *          g.setClip(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));<br/>
     *          g.setColor(myRippleColor);<br/>
     *          ripple.paint(g);<br/>
     *     }<br/>
     * }
     * </code>
     * @param target target component
     * @return ripple effect for that component
     * @see MaterialButton for an example of how the ripple effect is used
     */
    public static FCRippleEffect applyFixedTo(final JComponent target) {
        final FCRippleEffect rippleEffect = new FCRippleEffect(target);
        target.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rippleEffect.addRipple(new Point(24, 24), target.getWidth() / 2);
            }
        });
        return rippleEffect;
    }

    /**
     * A ripple animation (one ripple circle after one click).
     */
    public class RippleAnimation {
        private final Point rippleCenter;
        private final int maxRadius;
        private final FCSafePropertySetter.Property<Integer> rippleRadius = FCSafePropertySetter.animatableProperty(target, 25);
        private final FCSafePropertySetter.Property<Double> rippleOpacity = FCSafePropertySetter.animatableProperty(target, 0.0);

        private RippleAnimation(Point rippleCenter, int maxRadius) {
            this.rippleCenter = rippleCenter;
            this.maxRadius = maxRadius;
        }

        void start() {
            //rippleCenter.setLocation(rippleCenter);
            Animator rippleAnimator = new Animator.Builder(timer)
                    .setDuration(1000, TimeUnit.MILLISECONDS)
                    .setEndBehavior(Animator.EndBehavior.HOLD)
                    .setInterpolator(new AccelerationInterpolator(0.2, 0.19))
                    .addTarget(FCSafePropertySetter.getTarget(rippleRadius, 0, maxRadius / 2, maxRadius, maxRadius))
                    .addTarget(FCSafePropertySetter.getTarget(rippleOpacity, 0.0, 0.4, 0.3, 0.0))
                    .addTarget(new TimingTargetAdapter() {
                        @Override
                        public void end(Animator source) {
                            ripples.remove(RippleAnimation.this);
                        }
                    })
                    .build();
            rippleAnimator.start();
        }
    }
}
