import { DEFUIPage } from './app.po';

describe('def-ui App', () => {
  let page: DEFUIPage;

  beforeEach(() => {
    page = new DEFUIPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
