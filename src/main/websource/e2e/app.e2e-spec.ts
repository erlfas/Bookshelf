import { AsdfPage } from './app.po';

describe('asdf App', function() {
  let page: AsdfPage;

  beforeEach(() => {
    page = new AsdfPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
