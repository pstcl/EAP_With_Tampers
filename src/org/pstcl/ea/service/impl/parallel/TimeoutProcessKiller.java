package org.pstcl.ea.service.impl.parallel;

import java.util.TimerTask;

public class TimeoutProcessKiller extends TimerTask {
  private Process p;
  public TimeoutProcessKiller(Process p) {
    this.p = p;
  }

  public void run() {
    p.destroy();
  }
}