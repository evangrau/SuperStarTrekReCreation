public class setup {
    /* Lets make this for start of game */

    /* Klingon Creation */
    public class klingon {
        public double hp = 200;
        public double energy = 400;
        public int xquad, yquad, xsec, ysec;

        public klingon(int xq, int yq, int xs, int ys) {
            this.xquad = xq;
            this.yquad = yq;
            this.xsec = xs;
            this.ysec = ys;
        }
    }
}
